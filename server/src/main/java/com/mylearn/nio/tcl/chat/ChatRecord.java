package com.mylearn.nio.tcl.chat;

import com.mylearn.nio.tcl.chat.exception.ChatServerException;
import com.mylearn.nio.tcl.chat.utils.LogUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author peizicheng
 *   保存聊天记录的类
 */
public class ChatRecord implements Debugable{
    public static class RecordEntriy{
    	public Date mDate;
    	public static final int A_TO_B = 0;
    	public static final int B_TO_A = 1;
    	public int mDirection;
    	public String mText;
		@Override
		public String toString() {
			return "RecordEntriy [mDate=" + mDate + ", mDirection="
					+ mDirection + ", mText=" + mText + "]";
		}
    	
    }
    public String A_name;
    public String B_name;
    private List<RecordEntriy> mRecords;
    /**
     * 
     */
    public static final int MAX_SIZE = 100;
    /**
     * 
     * @param A 用户名A
     * @param B 用户名B
     */
    public ChatRecord(String A,String B){
    	this.A_name = A;
    	this.B_name = B;
    	this.mRecords = new LinkedList<RecordEntriy>();
    }
    /**
     * 
     * @param name 用户名
     * @param text 消息
     */
    public void addRecord(String name,String text){
    	if (name == null || text == null) {
			throw new IllegalArgumentException("用户名和消息不能为空");
		}
    	RecordEntriy entriy = new RecordEntriy();
    	entriy.mDate = new Date();
    	entriy.mText = text;
    	if (name.equals(A_name)) {
			entriy.mDirection = RecordEntriy.A_TO_B;
		}else if (name.equals(B_name)) {
			entriy.mDirection = RecordEntriy.B_TO_A;
		}else {
			throw new ChatServerException("错误的用户ID："+ name);
		}
    	mRecords.add(entriy);
    	if (mRecords.size() >= MAX_SIZE) {
			mRecords.clear();
			LogUtil.d("聊天记录已满，清空聊天记录");
		}
    }
    
	public void dump() {
		List<RecordEntriy> records = this.mRecords;
		System.out.println("dump ChatRecords:");
		System.out.println("Size:" + mRecords.size());
		for (RecordEntriy recordEntriy : records) {
			System.out.println(recordEntriy);
		}
	}
    
}
 
