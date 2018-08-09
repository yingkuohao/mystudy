package com.mylearn.nio.tcl.chat.command;

import com.mylearn.nio.tcl.chat.ChatServer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class QuitCmd extends Command {
	private static ByteBuffer BYE = ByteBuffer.wrap("bye".getBytes());
	@Override
	public void handleCmd(SelectionKey key, ChatServer server,String[] args) throws IOException {
		SocketChannel sc = (SocketChannel) key.channel();
		sc.write(BYE);
		BYE.flip();
		System.out.println("Say goodbye");
		String address = sc.socket().getRemoteSocketAddress()
				.toString();
		System.out.println("close: " + address);
		server.getChannelMap().remove(address);
		server.getLoginMemberMap().remove(address);
		sc.close();
		key.cancel();//cancel the key
	}

}
