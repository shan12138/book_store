package com.hgd.ebp.vi;

public class tempBook {
	private int bid;
	private int num;
	
	public tempBook(int bid, int num) {
		super();
		this.bid = bid;
		this.num = num;
	}
	
	public tempBook() {
		super();
	}
	
	public int getBid() {
		return bid;
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
}
