package com.mylearn.nio.tcl.chat.command;

import com.mylearn.nio.tcl.chat.ChatServer;

import java.io.IOException;
import java.nio.channels.SelectionKey;


public abstract class Command {
	public abstract void handleCmd(SelectionKey key , ChatServer server,String[] args) throws IOException;
}
