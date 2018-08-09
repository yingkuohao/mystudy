package com.mylearn.socket.nio;

import com.mylearn.socket.standard.LocalCons;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-27
 * Time: 下午5:28
 * CopyRight:360buy
 * Descrption:   NIO实现的socke服务端
 * 1. 打开一个选择器
 * 2. 打开服务端套接字管道
 * 3. 创建基于nio的socket链接，绑定端口
 * 4. 设为非阻塞模式，服务端管道注册accept事件。
 * 5. 轮询，找到I/O就绪的key（客户端准备就绪）
 * 6. 根据key得到对应的管道，通过accept方法，接受到此管道的套接字，得到一个socketChannel
 * 7. 注册socketChannel为read
 * 8. 客户端输入一个消息到socketchannel
 * 9. 服务端读取到buffer，判断是否为“quit”，然后做自己的事
 *
 * To change this template use File | Settings | File Templates.
 */
public class SocketServerNIO {
    static SelectionKey ssckey;

    public static void main(String args[]) {
        SocketServerNIO.server();
    }

    public static void server() {
        try {
            Selector selector = Selector.open(); //打开一个选择器
            ServerSocketChannel channel = ServerSocketChannel.open();//打开服务器套接字管道
            channel.configureBlocking(false);//设为非阻塞

            ServerSocket serverSocket = channel.socket(); //创建基于nio的socket链接
            serverSocket.bind(new InetSocketAddress(LocalCons.port));   //绑定端口

            ssckey = channel.register(selector, SelectionKey.OP_ACCEPT);//注册selector，selector对accept有兴趣

            while (true) {
                int readChannels = selector.select();  //选择一组key，其相应的I/O已准备就绪
                if (readChannels == 0)  //如果没有就绪的I/O，继续轮询
                    continue;
                Set selectedKeys = selector.selectedKeys();//获取已选择的键集
                Iterator keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {  //遍历键集， 处理对应的事件
                    SelectionKey curSelectkey = (SelectionKey) keyIterator.next();
                    if (curSelectkey.isAcceptable()) {
                        getConne(selector, curSelectkey);
                    } else if (curSelectkey.isConnectable()) {

                    } else if (curSelectkey.isReadable()) {
                        readMsg(selector, curSelectkey);
                    } else if (curSelectkey.isWritable()) {
                        writeMsg(selector, curSelectkey);
                    }
                    keyIterator.remove(); //移除此次事件
                }

            }


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    private static void getConne(Selector selector, SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();   //获取key上的通道
        try {
            SocketChannel sc = serverSocketChannel.accept();  //接受到此通道套接字的连接
            //注意这里要重新注册事件了
            sc.configureBlocking(false); //非阻塞
            sc.register(selector, SelectionKey.OP_READ);  //注册到selector，改为 read事件，selector对read有兴趣
            System.out.println("build connection :"
                    + sc.socket().getRemoteSocketAddress());

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void readMsg(Selector selector, SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuffer sb = new StringBuffer();
        try {
            int len = 0;
            //读取客户端写入channel中的数据
            while ((len = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                sb.append(new String(buffer.array(), 0, len));
            }
            System.out.println("读取信息" + sb.toString());

            //设置退出标识
            if ("quit".equals(sb.toString())) {
                //客户端申请退出，返回“BYE”,写入到通道，客户端读到后退出
                socketChannel.write(ByteBuffer.wrap("BYE".getBytes()));
                System.out.println("client is closed "
                        + socketChannel.socket().getRemoteSocketAddress());
                //注意关闭套接字
                key.cancel();
                socketChannel.close();
                socketChannel.socket().close();

            } else {

                Iterator<SelectionKey> it = key.selector().keys().iterator();
                //yingkhtodo:description:未知
                while (it.hasNext()) {
                    SelectionKey skey = it.next();
                    if (skey != key && skey != ssckey) {
                        if (skey.attachment() != null) {
                            String str = (String) skey.attachment();
                            skey.attach(str + sb.toString());
                        } else {
                            skey.attach(sb.toString());
                        }
                        skey
                                .interestOps(skey.interestOps()
                                        | SelectionKey.OP_WRITE);
                    }

                }
                selector.wakeup();// 可有可无
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private static void writeMsg(Selector selector, SelectionKey key) throws IOException {

        System.out.println("++++enter write+++");
        SocketChannel sc = (SocketChannel) key.channel();
        String str = (String) key.attachment();

        sc.write(ByteBuffer.wrap(str.getBytes()));
        key.interestOps(SelectionKey.OP_READ);
    }
}
