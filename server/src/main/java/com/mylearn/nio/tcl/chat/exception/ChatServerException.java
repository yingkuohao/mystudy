package com.mylearn.nio.tcl.chat.exception;

public class ChatServerException extends RuntimeException{
	public ChatServerException(String msg){
		super(msg);
	}
	public ChatServerException(Throwable ex){
		super(ex);
	}
}
