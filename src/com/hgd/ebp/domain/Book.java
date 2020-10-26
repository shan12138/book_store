package com.hgd.ebp.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class Book {
	private int bid;
	@NotEmpty(message="��������Ϊ��")
	private String bname;
	@NotEmpty(message="ͼƬ����Ϊ��")
	private String image;
	@NotEmpty(message="���߲���Ϊ��")
	private String author;
	@NotEmpty(message="��鲻��Ϊ��")
	private String descp;
	@NotNull(message="���۲���Ϊ��")
	@Range(min=0, message="����Ӧ����0")
	private double price;
	@NotEmpty(message="�����粻��Ϊ��")
	private String publisher;
	@NotEmpty(message="����ʱ�䲻��Ϊ��")
	private String publishTime;
	@NotEmpty(message="���಻��Ϊ��")
	private String type;
	private int quantity;
	
	public Book(int bid, String bname, String image, String author,
			String descp, double price, String publisher, String publishTime) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.image = image;
		this.author = author;
		this.descp = descp;
		this.price = price;
		this.publisher = publisher;
		this.publishTime = publishTime;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getBid(){
		return bid;
	}	
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public String getBname(){
		return bname;
	}	
	
	public void SetBname(String bname){
		this.bname=bname;
	}
	
	public String getImage(){
		return image;
	}	
	
	public void SetImage(String image){
		this.image=image;
	}
	
	public String getAuthor(){
		return author;
	}	
	
	public void SetAuthor(String author){
		this.author=author;
	}
	
	public String getDescp(){
		return descp;
	}	
	
	public void SetDescp(String descp){
		this.descp=descp;
	}
	
	public double getPrice(){
		return price;
	}	
	
	public void SetPrice(Double price){
		this.price= price;
	}
	
	public String getPublisher(){
		return publisher;
	}	
	
	public void SetPublisher(String publisher){
		this.publisher=publisher;
	}
	
	public String getPublishTime(){
		return publishTime;
	}	
	
	public void SetPublishTime(String publishTime){
		this.publishTime=publishTime;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Book(){
		
	}

	public Book(int bid, String bname, String image, String author,
			String descp, double price, String publisher, String publishTime,
			String type, int quantity) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.image = image;
		this.author = author;
		this.descp = descp;
		this.price = price;
		this.publisher = publisher;
		this.publishTime = publishTime;
		this.type = type;
		this.quantity = quantity;
	}

	public Book(String bname, String image, String author,
			String descp, double price, String publisher, String publishTime,
			String type) {
		super();
		this.bname = bname;
		this.image = image;
		this.author = author;
		this.descp = descp;
		this.price = price;
		this.publisher = publisher;
		this.publishTime = publishTime;
		this.type = type;
	}
	
	
}
