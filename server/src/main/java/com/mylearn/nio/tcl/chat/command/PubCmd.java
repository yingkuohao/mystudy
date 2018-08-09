package com.mylearn.nio.tcl.chat.command;

import com.mylearn.nio.tcl.chat.ChatServer;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class PubCmd extends Command {

	@Override
	public void handleCmd(SelectionKey key, ChatServer server, String[] args)
			throws IOException {
		SocketChannel sc = (SocketChannel) key.channel();
		StringBuilder sb = new StringBuilder();
		if (args.length <= 1) {
			return;
		} else {
			for (int i = 1; i < args.length; i++) {
				sb.append(args[i]);
			}
		}

		String pub_msg = sc.socket().getRemoteSocketAddress() + ": "
				+ sb.toString();
		System.out.println(pub_msg);
		Iterator<SelectionKey> it = key.selector().keys().iterator();
		while (it.hasNext()) {
			SelectionKey sKey = (SelectionKey) it.next();
			if ((sKey != key) && (sKey != server.getServerKey())) {
				String address = ((SocketChannel) sKey.channel()).socket()
						.getRemoteSocketAddress().toString();
				if (server.getLoginMemberMap().get(address) == null) {
					return;// 未登陆成功的终端看不到消息
				}
				if (sKey.attachment() == null) {
					sKey.attach(pub_msg);
				} else {
					String at = (String) sKey.attachment();
					sKey.attach(at + pub_msg);
				}
				sKey.interestOps(sKey.interestOps() | SelectionKey.OP_WRITE);
			}
		}
	}

}
