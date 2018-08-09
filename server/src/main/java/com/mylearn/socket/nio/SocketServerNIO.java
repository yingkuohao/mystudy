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
 * Time: ����5:28
 * CopyRight:360buy
 * Descrption:   NIOʵ�ֵ�socke�����
 * 1. ��һ��ѡ����
 * 2. �򿪷�����׽��ֹܵ�
 * 3. ��������nio��socket���ӣ��󶨶˿�
 * 4. ��Ϊ������ģʽ������˹ܵ�ע��accept�¼���
 * 5. ��ѯ���ҵ�I/O������key���ͻ���׼��������
 * 6. ����key�õ���Ӧ�Ĺܵ���ͨ��accept���������ܵ��˹ܵ����׽��֣��õ�һ��socketChannel
 * 7. ע��socketChannelΪread
 * 8. �ͻ�������һ����Ϣ��socketchannel
 * 9. ����˶�ȡ��buffer���ж��Ƿ�Ϊ��quit����Ȼ�����Լ�����
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
            Selector selector = Selector.open(); //��һ��ѡ����
            ServerSocketChannel channel = ServerSocketChannel.open();//�򿪷������׽��ֹܵ�
            channel.configureBlocking(false);//��Ϊ������

            ServerSocket serverSocket = channel.socket(); //��������nio��socket����
            serverSocket.bind(new InetSocketAddress(LocalCons.port));   //�󶨶˿�

            ssckey = channel.register(selector, SelectionKey.OP_ACCEPT);//ע��selector��selector��accept����Ȥ

            while (true) {
                int readChannels = selector.select();  //ѡ��һ��key������Ӧ��I/O��׼������
                if (readChannels == 0)  //���û�о�����I/O��������ѯ
                    continue;
                Set selectedKeys = selector.selectedKeys();//��ȡ��ѡ��ļ���
                Iterator keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {  //���������� �����Ӧ���¼�
                    SelectionKey curSelectkey = (SelectionKey) keyIterator.next();
                    if (curSelectkey.isAcceptable()) {
                        getConne(selector, curSelectkey);
                    } else if (curSelectkey.isConnectable()) {

                    } else if (curSelectkey.isReadable()) {
                        readMsg(selector, curSelectkey);
                    } else if (curSelectkey.isWritable()) {
                        writeMsg(selector, curSelectkey);
                    }
                    keyIterator.remove(); //�Ƴ��˴��¼�
                }

            }


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    private static void getConne(Selector selector, SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();   //��ȡkey�ϵ�ͨ��
        try {
            SocketChannel sc = serverSocketChannel.accept();  //���ܵ���ͨ���׽��ֵ�����
            //ע������Ҫ����ע���¼���
            sc.configureBlocking(false); //������
            sc.register(selector, SelectionKey.OP_READ);  //ע�ᵽselector����Ϊ read�¼���selector��read����Ȥ
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
            //��ȡ�ͻ���д��channel�е�����
            while ((len = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                sb.append(new String(buffer.array(), 0, len));
            }
            System.out.println("��ȡ��Ϣ" + sb.toString());

            //�����˳���ʶ
            if ("quit".equals(sb.toString())) {
                //�ͻ��������˳������ء�BYE��,д�뵽ͨ�����ͻ��˶������˳�
                socketChannel.write(ByteBuffer.wrap("BYE".getBytes()));
                System.out.println("client is closed "
                        + socketChannel.socket().getRemoteSocketAddress());
                //ע��ر��׽���
                key.cancel();
                socketChannel.close();
                socketChannel.socket().close();

            } else {

                Iterator<SelectionKey> it = key.selector().keys().iterator();
                //yingkhtodo:description:δ֪
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
                selector.wakeup();// ���п���
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
