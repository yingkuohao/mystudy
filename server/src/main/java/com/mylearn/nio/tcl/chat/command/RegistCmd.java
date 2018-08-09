package com.mylearn.nio.tcl.chat.command;

import com.mylearn.nio.tcl.chat.ChatServer;
import com.mylearn.nio.tcl.chat.Member;
import com.mylearn.nio.tcl.chat.utils.LogUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;


public class RegistCmd extends Command {
	private static ByteBuffer ERROR = ByteBuffer
			.wrap("Error regist username and password".getBytes());
	private static ByteBuffer SUCCESS = ByteBuffer.wrap("Regist success"
			.getBytes());

	@Override
	public void handleCmd(SelectionKey key, ChatServer server, String[] args)
			throws IOException {
		// TODO Auto-generated method stub
		LogUtil.i("Regist CMD :");
		SocketChannel sc = (SocketChannel) key.channel();
		LogUtil.e("args length " + args.length);
		if (args.length == 3) {
			String username = args[1];
			String password = args[2];
			Member mb = new Member(username, password);
			boolean flag = server.getMemberStorage().register(mb);
			if (flag) {
				sc.write(SUCCESS);
				SUCCESS.flip();
				return;
			}
		}
		sc.write(ERROR);
		ERROR.flip();
		return;
	}

}
