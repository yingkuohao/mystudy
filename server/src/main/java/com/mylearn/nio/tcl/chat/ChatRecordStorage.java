package com.mylearn.nio.tcl.chat;

public interface ChatRecordStorage {
	
	public ChatRecord get(String A, String B);
	public void save(String A, String B, String text);
	
}
