package com.mylearn.nio.tcl.chat.model;

import java.io.Serializable;

public class Message implements Serializable{
	public static String PUBLIC = "public";
	public String from;
	public String to;
	public String message;
}
