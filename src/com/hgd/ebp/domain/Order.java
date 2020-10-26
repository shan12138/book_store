package com.hgd.ebp.domain;


public class Order {
	private int id;
	private int oid;
	private String descp;
	private java.sql.Timestamp commitTime;
	private double amount;
	private String name;
	private String userName;
	private String idCard;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public java.sql.Timestamp getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(java.sql.Timestamp commitTime) {
		this.commitTime = commitTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Order(int id, int oid, String descp, java.sql.Timestamp commitTime2,
			double amount, String name, String username, String idCard) {
		super();
		this.id = id;
		this.oid = oid;
		this.descp = descp;
		this.commitTime = commitTime2;
		this.amount = amount;
		this.name = name;
		this.userName = username;
		this.idCard = idCard;
	}
	
	public Order(){
		
	}
	
	public Order(int oid,java.sql.Timestamp commitTime,double amount){
		this.oid = oid;
		this.commitTime = commitTime;
		this.amount = amount;
		
	}
}
