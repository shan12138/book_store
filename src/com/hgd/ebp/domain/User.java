package com.hgd.ebp.domain;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private int uid;
	@NotEmpty(message="用户名不能为空")
	private String userName;
	@NotEmpty(message="密码不能为空")
	private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty(message="确认密码不能为空")
	private String passwordagain;
	@NotEmpty(message="真实姓名不能为空")
	private String uname;
	@NotEmpty(message="性别不能为空")
	private String gender;
	@NotEmpty(message="身份证号不能为空")
	private String idCard;
	@NotEmpty(message="地址不能为空")
	private String address;
	@NotEmpty(message="电话号码不能为空")
	private String telNo;
	private Timestamp regTime;
	private double balance;
	private int status;
	private String images;
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public User(){
		
	}
	
	public User(String username, String password){
		this.userName = username;
		this.password = password;
	}
	
	public User(int uid, String username,String name,
			String gender, String idcard, String address, String telno,
			double balance, int status, String images) {
		this.uid = uid;
		this.userName = username;
		this.uname = name;
		this.gender = gender;
		this.idCard = idcard;
		this.address = address;
		this.telNo = telno;
		this.balance = balance;
		this.status = status;
		this.images = images;
	}
	
	public User(int uid, String username, String password, String name,
			String gender, String idcard, String address, String telno,
			Timestamp regtime, double balance, int status) {
		this.uid = uid;
		this.userName = username;
		this.password = password;
		this.uname = name;
		this.gender = gender;
		this.idCard = idcard;
		this.address = address;
		this.telNo = telno;
		this.regTime = regtime;
		this.balance = balance;
		this.status = status;
	}
	
	public User(int uid, String username, String name, String gender,
			String idcard, String address, String telno, Timestamp regtime,
			double balance, int status) {
		super();
		this.uid = uid;
		this.userName = username;
		this.uname = name;
		this.gender = gender;
		this.idCard = idcard;
		this.address = address;
		this.telNo = telno;
		this.regTime = regtime;
		this.balance = balance;
		this.status = status;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPasswordagain() {
		return passwordagain;
	}

	public void setPasswordagain(String passwordagain) {
		this.passwordagain = passwordagain;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idcard) {
		this.idCard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telno) {
		this.telNo = telno;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regtime) {
		this.regTime = regtime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	
}
