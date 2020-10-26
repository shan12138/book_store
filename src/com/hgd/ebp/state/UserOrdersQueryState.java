package com.hgd.ebp.state;

public class UserOrdersQueryState extends PageQueryState{
	private int uid;

	
	public UserOrdersQueryState() {}

	public UserOrdersQueryState(int uid) {
		super();
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
}