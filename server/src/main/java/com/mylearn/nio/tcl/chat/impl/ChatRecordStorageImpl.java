package com.mylearn.nio.tcl.chat.impl;

import com.mylearn.nio.tcl.chat.ChatRecord;
import com.mylearn.nio.tcl.chat.ChatRecordStorage;
import com.mylearn.nio.tcl.chat.Debugable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ChatRecordStorageImpl implements ChatRecordStorage,Debugable {

	public ChatRecord get(String A, String B) {
		String key = getKey(A, B);
		return mMap.get(key);
	}

	public void save(String A, String B, String text) {
		String key = getKey(A, B);
		ChatRecord record = mMap.get(key);
		if (record != null) {
			record.addRecord(A, text);
		}else {
			record = new ChatRecord(A, B);
			mMap.put(getKey(A, B), record);
			record.addRecord(A, text);
		}
	}

	public ChatRecordStorageImpl() {

	}

	private Map<String, ChatRecord> mMap = new HashMap<String, ChatRecord>();

	private String getKey(String A, String B) {
		if (A == null || B == null) {
			throw new IllegalArgumentException("A B ²»ÄÜÎª null");
		}
		String key;
		if (A.compareTo(B) > 0) {
			key = A + " to " + B;
		} else {
			key = B + " to " + A;
		}
		return key;
	}
	
	public void dump(){
		Set<String> set = mMap.keySet();
		System.out.println("dump ChatRecordStorage");
		for (String key : set) {
			System.out.println(key);
			ChatRecord record = mMap.get(key);
			record.dump();
		}
	}
	
	
}
