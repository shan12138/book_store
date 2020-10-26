package com.hgd.ebp.domain;

public class ShoppingTicket {
	private int tid;
	private String descp;
	private java.sql.Timestamp startTime;
	private int amount;
	private int balance;
    private double price;
    private int staus;
    private int number;
    private double total_price;
    
    public ShoppingTicket(){
    	
    }
    public ShoppingTicket(int number, double total_price){ 	
    	this.number = number;
    	this.total_price = total_price;
    }
    public ShoppingTicket(int tid,String descp,java.sql.Timestamp startTime,int amount,int balance, double price,  int staus,int number,double total_price){
    	this.tid=tid;
    	this.descp = descp;
    	this.startTime=startTime;
    	this.amount=amount;
    	this.balance=balance;
    	this.price =price;
    	this.staus=staus;
    	this.number=number;
    	this.total_price=total_price;
    }
    
    public int getTid()
    {
    	return tid;
    }
    public String getDescp(){
    	return descp;
    }
    
    public java.sql.Timestamp  getStartTime()
    {
    	return startTime;
    }
    
    public int getAmount(){
    	return amount;
    }
    
    public int getBalance(){
    	return balance;
    }
    
    public double getPrice(){
    	return price;
    }
    
    public int getStaus(){
    	return staus;
    }
    
    public int getNumber(){
    	return number;
    }
    public double getTotal_price(){
    	return total_price;
    }
    
    public void setTid(int tid) {
		this.tid= tid;
	}
    
    public  void setStarttTime( java.sql.Timestamp startTime){
    	this.startTime=startTime;
    }
    
    public void setDescp(String descp){
    	this.descp=descp;
    }
    public void setAmount(int amount) {
		this.amount = amount;
	}
    
    public void setBalance(int balance) {
		this. balance =  balance;
	}
   
    public void setPrice(double price) {
		this.price = price;
	}
    
    public void setStaus(int staus) {
		this.staus = staus;
	}
    
    public void setNumber(int number){
    	this.number=number;
    }
    
    public void setTotal_price(double total_price){
    	this.total_price=total_price;
    }
    
    

}
