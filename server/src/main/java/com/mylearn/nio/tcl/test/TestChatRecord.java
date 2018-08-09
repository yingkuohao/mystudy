package com.mylearn.nio.tcl.test;


import com.mylearn.nio.tcl.chat.impl.ChatRecordStorageImpl;

public class TestChatRecord {
	public static void main(String[] args) {
		ChatRecordStorageImpl storageImpl = new ChatRecordStorageImpl();
		storageImpl.save("Jim", "Lucy", "Hi,lucy");
		storageImpl.save("Lucy", "Jim", "Hi,lucy");
		storageImpl.save("Jim", "Lily", "Hi,lucy");
		storageImpl.save("Jim", "Jack", "Hi,lucy");
		storageImpl.save("Jack", "Lucy", "Hi,lucy");
		storageImpl.save("Jack", "Lily", "Hi,lucy");
		storageImpl.save("Lily", "Lucy", "Hi,lucy");
		storageImpl.dump();
	}
}
