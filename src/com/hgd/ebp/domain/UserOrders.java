package com.hgd.ebp.domain;

import java.sql.Timestamp;

public class UserOrders {
	private int oid;
	private int lid;
	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	private String descp;
	private double price;
	private int quantity;
	private double amount;
	private java.sql.Timestamp commitTime;
	private String image;
	
	public UserOrders() {}

	public UserOrders(int oid, String descp, double unitprice, int quantity,
			double amount, Timestamp commitTime ,String image) {
		super();
		this.oid = oid;
		this.descp = descp;
		this.price = unitprice;
		this.quantity = quantity;
		this.amount = amount;
		this.commitTime = commitTime;
		this.image = image;
	}

	public UserOrders(int lid, int oid, String descp, double price,
			int quantity, double amount) {
		super();
		this.lid = lid;
		this.oid = oid;
		this.descp = descp;
		this.price = price;
		this.quantity = quantity;
		this.amount = amount;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setUnitprice(double unitprice) {
		this.price = unitprice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public java.sql.Timestamp getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(java.sql.Timestamp commitTime) {
		this.commitTime = commitTime;
	}

}
