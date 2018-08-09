package com.mylearn.nio.tcl.chat;

import com.mylearn.nio.tcl.chat.impl.ChatRecordStorageImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChatClient implements Runnable{
	private Logger log = Logger.getLogger(ChatClient.class.getName());
    SocketChannel mChannel;
    boolean bRunFlag = true;
    private boolean bIsInit = false;
    String mHostname;
    int  mPort;
    
    private ChatRecordStorage mChatStorage = new ChatRecordStorageImpl();
    
    public ChatClient(String host,int port){
    	mHostname = host;
    	mPort = port;
    }
    private static final int TRY_TIMES = 3;
	public void init() throws IOException{
			mChannel =  SocketChannel.open();
			mChannel.configureBlocking(false);  
			mChannel.connect(new InetSocketAddress(mHostname,mPort)); 
			int i = 0;
			boolean error = false;
			while (!mChannel.finishConnect() ) {
				if (i >= TRY_TIMES) {
					error = true;
					break;
				}
				i++;
				//
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (error) {
				throw new IOException("连接错误，稍后再试");
			}
			bIsInit = true;
	}
	
	public void run() {
		try {
			init();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		if (!bIsInit) {
			throw new RuntimeException("not init");
		}
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		KeyBordReader kbr = new KeyBordReader();
		new Thread(kbr).start();//阻塞方式的读
		
		while (bRunFlag) {
			buffer.clear();
			StringBuffer sb = new StringBuffer();
			int num;
			try {
				Thread.sleep(500);
				while((num = mChannel.read(buffer)) > 0){
					sb.append(new String(buffer.array(),0,num));
					buffer.clear();
				}
				if (sb.length() > 0 ) {
					log.log(Level.INFO,"Client Recerive:" + sb.toString());
				}
				if (sb.toString().toLowerCase().trim().equals("bye")) {
					mChannel.close();
					kbr.close();
					bRunFlag = false;
					System.exit(0); //退出
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) {
		int port = 10003;
		new Thread(new ChatClient("localhost", port)).start();
	}
	
	class KeyBordReader implements Runnable{
		public KeyBordReader(){
			
		}
		private boolean bRunFlag = true;
		public void close(){
			bRunFlag = false;
		}
		public void run() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while (this.bRunFlag) {
				//System.out.println(TIP);
				try {
					String str = reader.readLine().trim();
					if (str == null) {
						continue;
					}
					if (!str.startsWith("@")) {
						System.out.println("must begin with @");
						continue;
					}else if (str.equals("@help")) {
						System.out.println(TIP);
					}else if (str.equals("@exit")) {
						System.exit(0);
					}
					mChannel.write(ByteBuffer.wrap(str.getBytes()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	private static String TIP = "" 
						+ "@pub to send public message\n"
						+ "@xxx to send private message \n"
						+ "@exit to exit client\n"
						+ "@quit to quit from server\n"
						+ "@listmember to list members";
			
}
