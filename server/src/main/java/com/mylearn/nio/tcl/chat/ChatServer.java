package com.mylearn.nio.tcl.chat;

import com.mylearn.nio.tcl.chat.command.Command;
import com.mylearn.nio.tcl.chat.command.CommandFactory;
import com.mylearn.nio.tcl.chat.exception.ChatServerException;
import com.mylearn.nio.tcl.chat.impl.MemberStorageImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;



/**
 * nio 设置一个聊天室程序
 * 
 * @author peizicheng
 * 
 */
public class ChatServer implements Runnable {
	
	private Logger log = Logger.getLogger(ChatServer.class.getName());
	
	private Config mConfig;
	private NioHandler mIoHandler;
	private ServerSocketChannel mSSC;
	private SelectionKey mSSCKey;
	public ChatServer(Config config) {
		this.mConfig = config;
		this.mIoHandler = new ChatIoHandler();
		this.mChannelMap = new HashMap<String, SocketChannel>();
		this.mLoginMemberMap = new HashMap<String, Member>();//port to member 映射
		this.mMemberStorage = new MemberStorageImpl(20);
	}

	public static class Config {
		public int port;
	}

	Map<String, SocketChannel> mChannelMap;//socket address 到 SocketChannel的映射
	Map<String, Member>  mLoginMemberMap; //socket address  到 member的映射；
	MemberStorage mMemberStorage; //所有会员的记录；
	
	public Map<String, Member> getLoginMemberMap(){
		return mLoginMemberMap;
	}
	
	
	public MemberStorage getMemberStorage() {
		return mMemberStorage;
	}
	public Map<String, SocketChannel> getChannelMap(){
		return mChannelMap;
	}
	public SelectionKey getServerKey(){
		return mSSCKey;
	}
	static interface NioHandler {
		/**
		 * 处理{@link java.nio.channels.SelectionKey#OP_ACCEPT}事件
		 * 
		 * @param key
		 * @throws java.io.IOException
		 */
		void handleAccept(SelectionKey key) throws IOException;

		/**
		 * 处理{@link java.nio.channels.SelectionKey#OP_READ}事件
		 * 
		 * @param key
		 * @throws java.io.IOException
		 */
		void handleRead(SelectionKey key) throws IOException;

		/**
		 * 处理{@link java.nio.channels.SelectionKey#OP_WRITE}事件
		 * 
		 * @param key
		 * @throws java.io.IOException
		 */
		void handleWrite(SelectionKey key) throws IOException;
		/**
		 * 处理IO 异常事件
		 * @param key 
		 * @throws java.io.IOException
		 */
		void handleIoError(SelectionKey key) throws IOException;
	}

	class ChatIoHandler implements NioHandler {
		StringBuffer sb = new StringBuffer();

		public void handleAccept(SelectionKey key) throws IOException {
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			SocketChannel client = ssc.accept();
			if (client != null) {
				client.configureBlocking(false);
				System.out.println("Accept:"
						+ client.socket().getRemoteSocketAddress().toString());
				client.register(key.selector(), SelectionKey.OP_READ);
				String address = client.socket().getRemoteSocketAddress()
						.toString();
				ChatServer.this.mChannelMap.put(address, client); // 加入map中
			}
			
		}

		public void handleRead(SelectionKey key) throws IOException {
			SocketChannel sc = (SocketChannel) key.channel();
			System.out.println("Read from"
					+ sc.socket().getRemoteSocketAddress() + " ");
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.clear();
			int len = 0;
			StringBuffer sBuffer = new StringBuffer();
			while ((len = sc.read(buffer)) > 0) {
				buffer.flip();
				sBuffer.append(new String(buffer.array(), 0, len));
			}
			//handle socket error;
			if (len < 0) {
				handleIoError(key);
			}
			String msg = sBuffer.toString();
			if (sBuffer.length() > 0) {
				System.out.println("Server receive:" + sBuffer.toString());
			}
			//command 设计模式
			String[] args = msg.split(" ");
			Command cmd = CommandFactory.getInstance().createCommand(args[0]);
			cmd.handleCmd(key, ChatServer.this,args);
		}

		public void handleWrite(SelectionKey key) throws IOException {
			// TODO Auto-generated method stub
			SocketChannel  sc = (SocketChannel) key.channel();
			String address = sc.socket().getRemoteSocketAddress().toString();
			System.out.println("handleWrite: " + address);
			String msg = (String) key.attachment();
			if (msg != null) {
				int len = sc.write(ByteBuffer.wrap(msg.getBytes()));
				if (len < 0) {
					//handle error;
					handleIoError(key);
				}
				key.attach(null);
				key.interestOps(SelectionKey.OP_READ);
			}
		}

		public void handleIoError(SelectionKey key) throws IOException {
			//handle error;
			SocketChannel sc = (SocketChannel) key.channel();
			String address = sc.socket().getRemoteSocketAddress().toString();
			sc.close();
			key.cancel(); //cancel key 
			mChannelMap.remove(address);//remove from map
			mLoginMemberMap.remove(address);//remove from login map
			log.log(Level.INFO, "Disconnect with " + address);
		}

	}

	public void run() {
		InetSocketAddress address = new InetSocketAddress(mConfig.port);
		try {
			mSSC  = ServerSocketChannel.open();
			Selector selector = Selector.open();
			mSSC.socket().bind(address);
			mSSC.configureBlocking(false);
			mSSCKey = mSSC.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				int events = selector.select();
				if (events == 0) {
					continue;
				}
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while(it.hasNext()) {
					SelectionKey key = it.next();
					it.remove();
					if (key.isAcceptable()) {
						mIoHandler.handleAccept(key);
					} else if (key.isReadable()) {
						mIoHandler.handleRead(key);
					} else if (key.isWritable()) {
						mIoHandler.handleWrite(key);
					}else {
						break;
					}
				}
				Thread.yield();// 让出执行空间
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ChatServerException(e);
		}

	}
	public static void main(String[] args) {
		int port = 10003;
		Config config = new Config();
		config.port = port;
		new Thread(new ChatServer(config)).start();
	}
}
