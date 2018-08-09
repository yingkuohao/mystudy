package com.mylearn.nio.example;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * NIO�����
 * 
 * @author shirdrn
 */
public class NioTcpServer extends Thread {

	private static final Logger log = Logger.getLogger(NioTcpServer.class.getName());
	private InetSocketAddress inetSocketAddress;
	private Handler handler = new ServerHandler();
	
	public NioTcpServer(String hostname, int port) {
		inetSocketAddress = new InetSocketAddress(hostname, port);
	}
	
	@Override
	public void run() {
		try {
			Selector selector = Selector.open(); // ��ѡ����
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // ��ͨ��
			serverSocketChannel.configureBlocking(false); // ������
			serverSocketChannel.socket().bind(inetSocketAddress);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // ��ͨ��ע��ѡ�����Ͷ�Ӧ�¼���ʶ
			log.info("Server: socket server started.");
			while(true) { // ��ѯ
				int nKeys = selector.select();
				if(nKeys>0) {
					Set<SelectionKey> selectedKeys = selector.selectedKeys();
					Iterator<SelectionKey> it = selectedKeys.iterator();
					while(it.hasNext()) {
						SelectionKey key = it.next();
						if(key.isAcceptable()) {
							log.info("Server: SelectionKey is acceptable.");
							handler.handleAccept(key);
						} else if(key.isReadable()) {
							log.info("Server: SelectionKey is readable.");
							handler.handleRead(key);
						} else if(key.isWritable()) {
							log.info("Server: SelectionKey is writable.");
							handler.handleWrite(key);
						}
						it.remove();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �򵥴������ӿ�
	 * 
	 * @author shirdrn
	 */
	interface Handler {
		/**
		 * ����{@link java.nio.channels.SelectionKey#OP_ACCEPT}�¼�
		 * @param key 
		 * @throws java.io.IOException
		 */
		void handleAccept(SelectionKey key) throws IOException;
		/**
		 * ����{@link java.nio.channels.SelectionKey#OP_READ}�¼�
		 * @param key 
		 * @throws java.io.IOException
		 */
		void handleRead(SelectionKey key) throws IOException;
		/**
		 * ����{@link java.nio.channels.SelectionKey#OP_WRITE}�¼�
		 * @param key 
		 * @throws java.io.IOException
		 */
		void handleWrite(SelectionKey key) throws IOException;
	}
	
	/**
	 * ������¼�����ʵ����
	 * 
	 * @author shirdrn
	 */
	class ServerHandler implements Handler {

		@Override
		public void handleAccept(SelectionKey key) throws IOException {
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
			SocketChannel socketChannel = serverSocketChannel.accept();
			log.info("Server: accept client socket " + socketChannel);
			socketChannel.configureBlocking(false);
			socketChannel.register(key.selector(), SelectionKey.OP_READ);
		}

		@Override
		public void handleRead(SelectionKey key) throws IOException {
			ByteBuffer byteBuffer = ByteBuffer.allocate(512);
			SocketChannel socketChannel = (SocketChannel)key.channel();
			while(true) {
				int readBytes = socketChannel.read(byteBuffer);
				if(readBytes>0) {
					log.info("Server: readBytes = " + readBytes);
					log.info("Server: data = " + new String(byteBuffer.array(), 0, readBytes));
					byteBuffer.flip();
					socketChannel.write(byteBuffer);
					break;
				}
			}
			socketChannel.close();
		}

		@Override
		public void handleWrite(SelectionKey key) throws IOException {
			ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
			byteBuffer.flip();
			SocketChannel socketChannel = (SocketChannel)key.channel();
			socketChannel.write(byteBuffer);
			if(byteBuffer.hasRemaining()) {
				key.interestOps(SelectionKey.OP_READ);
			}
			byteBuffer.compact();
		}
	}

	public static void main(String[] args) {
		NioTcpServer server = new NioTcpServer("localhost", 10008);
		server.start();
	}
}
