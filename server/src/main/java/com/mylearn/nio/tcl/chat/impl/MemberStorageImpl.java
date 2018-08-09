package com.mylearn.nio.tcl.chat.impl;

import com.mylearn.nio.tcl.chat.Member;
import com.mylearn.nio.tcl.chat.MemberStorage;

import java.util.HashMap;


public class MemberStorageImpl implements MemberStorage {
	private HashMap<String, Member> mStorage;
	
	public  MemberStorageImpl(int size){
		mStorage = new HashMap<String, Member>(size);
	}

	public boolean register(Member m) {
		if (has(m.getUsername())) {
			return false;
		}else {
			mStorage.put(m.getUsername(), m);
		}
		return true;
	}

	public boolean has(String name) {
		Member m = mStorage.get(name);
		if (m == null) {
			return false;
		}else {
			return true;
		}
	}

	public Member get(String name) {
		return mStorage.get(name);
	}
}
