package com.mylearn.nio.tcl.chat.command;

import com.mylearn.nio.tcl.chat.ChatServer;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Set;


public class ListNameCmd extends Command{
	private static final String SPLIT = ";";
	@Override
	public void handleCmd(SelectionKey key, ChatServer server, String[] args)
			throws IOException {
		Map<String, SocketChannel> map = server.getChannelMap();
		Set<String> members = map.keySet();
		StringBuilder sb = new StringBuilder();
		for (String string : members) {
			sb.append(string);
			sb.append(SPLIT);
		}
		String msg = (String) key.attachment();
		if (msg == null) {
			key.attach(sb.toString());
		}else {
			key.attach(msg + sb.toString());
		}
		key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
	}

}
