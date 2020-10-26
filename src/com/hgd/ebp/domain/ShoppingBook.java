package com.hgd.ebp.domain;

public class ShoppingBook {
	private int bid;
	private String bname;
	private double  price;
    private int quantity;
    private double amount;
    
    
	public String getBname() {
		return bname;
	}

	public ShoppingBook(int bid,String bname, double price, int quantity, double amount) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.price = price;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	public ShoppingBook(int bid, int quantity) {
		super();
		this.bid = bid;
		this.quantity = quantity;
	}
	
	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    

}
