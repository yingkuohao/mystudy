package com.mylearn.nio.tcl.chat.command;

import com.mylearn.nio.tcl.chat.ChatServer;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandFactory {
	
	private Logger log = Logger.getLogger(CommandFactory.class.getName());
	private static Map<String, Command> sCMD_MAP = new HashMap<String, Command>();
	private static Command sDefaultCommand = new Command() {

		public void handleCmd(SelectionKey key, ChatServer server, String[] args)
				throws IOException {
			//do nothing 
		}
		
		
	};
	private static CommandFactory INSTANCE = null;
	private CommandFactory(){
		sCMD_MAP.put("@quit", new QuitCmd());
		sCMD_MAP.put("@listmember", new ListMemberCmd());
		sCMD_MAP.put("@pub", new PubCmd());
		sCMD_MAP.put("@regist", new RegistCmd());
		sCMD_MAP.put("@login", new LoginCmd());
	}
	public static CommandFactory getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new CommandFactory();
		}
		return INSTANCE;
	}
	public  Command createCommand(String msg) {
		
		log.log(Level.INFO, msg);
		Command cmd = sCMD_MAP.get(msg);
		if (cmd == null) {
			return sDefaultCommand;
		}
		return cmd;
	}
}
