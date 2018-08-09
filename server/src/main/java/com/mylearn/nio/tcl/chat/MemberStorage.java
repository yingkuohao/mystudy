package com.mylearn.nio.tcl.chat;

public interface MemberStorage {
	public boolean register(Member m);
	public boolean has(String m);
	public Member get(String name);
}
