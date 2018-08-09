package com.mylearn.nio.tcl.chat.command;

import com.mylearn.nio.tcl.chat.ChatServer;
import com.mylearn.nio.tcl.chat.Member;
import com.mylearn.nio.tcl.chat.utils.LogUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;


public class LoginCmd extends Command {
	private static ByteBuffer ERROR = ByteBuffer
			.wrap("Error username and password".getBytes());
	private static ByteBuffer SUCCESS = ByteBuffer.wrap("Login success"
			.getBytes());

	@Override
	public void handleCmd(SelectionKey key, ChatServer server, String[] args)
			throws IOException {
		// TODO Auto-generated method stub
		LogUtil.i("Login CMD :");
		SocketChannel sc = (SocketChannel) key.channel();
		if (args.length == 3) {
			String username = args[1];
			String password = args[2];
			Member mb = server.getMemberStorage().get(username);
			if (mb != null) {
				
				boolean flag = mb.login(username, password);
				if (flag) {
					sc.write(SUCCESS);
					SUCCESS.flip();
					server.getLoginMemberMap() //add to login map
							.put(sc.socket().getRemoteSocketAddress()
									.toString(), mb);
					return;
				}
			}
		}
		sc.write(ERROR);
		ERROR.flip();
		return;
	}
}
