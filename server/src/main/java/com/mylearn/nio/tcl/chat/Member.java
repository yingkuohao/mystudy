package com.mylearn.nio.tcl.chat;

import java.lang.Thread.State;

/**
 * 会员类
 * 
 * @author tmdpzc
 * 
 */
public class Member {
	private String mUsername;
	private String mPassword;

	private MemberState mState;
	/**
	 * 定义会员的状态
	 * @author tmdpzc
	 *
	 */
	static class MemberState {
		public static final int OFFLINE = 0;
		public static final int UNLOGIN = 1;
		public static final int ONLINE = 2;
		public int state = 0;

		public MemberState() {
			// do nothing
		}

		public void setState(int state) {
			this.state = state;
		}

	}

	public boolean login(String username, String password) {
		if (this.mUsername.equals(username) && this.mPassword.equals(password)) {
			online();
			return true;
		}
		return false;
	}
	public void connected(){
		mState.setState(MemberState.UNLOGIN);
	}
	
	private void online() {
		mState.setState(MemberState.ONLINE);
	}

	public void offline() {
		mState.setState(MemberState.OFFLINE);
	}

	public Member(String username, String password) {
		super();
		this.mUsername = username;
		this.mPassword = password;
		this.mState = new MemberState();
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		this.mUsername = username;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String password) {
		this.mPassword = password;
	}

}
