package com.mylearn.nio.tcl.chat;

import com.mylearn.nio.tcl.chat.exception.ChatServerException;
import com.mylearn.nio.tcl.chat.utils.LogUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author peizicheng
 *   ���������¼����
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
     * @param A �û���A
     * @param B �û���B
     */
    public ChatRecord(String A,String B){
    	this.A_name = A;
    	this.B_name = B;
    	this.mRecords = new LinkedList<RecordEntriy>();
    }
    /**
     * 
     * @param name �û���
     * @param text ��Ϣ
     */
    public void addRecord(String name,String text){
    	if (name == null || text == null) {
			throw new IllegalArgumentException("�û�������Ϣ����Ϊ��");
		}
    	RecordEntriy entriy = new RecordEntriy();
    	entriy.mDate = new Date();
    	entriy.mText = text;
    	if (name.equals(A_name)) {
			entriy.mDirection = RecordEntriy.A_TO_B;
		}else if (name.equals(B_name)) {
			entriy.mDirection = RecordEntriy.B_TO_A;
		}else {
			throw new ChatServerException("������û�ID��"+ name);
		}
    	mRecords.add(entriy);
    	if (mRecords.size() >= MAX_SIZE) {
			mRecords.clear();
			LogUtil.d("�����¼��������������¼");
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
 
