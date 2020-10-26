package com.hgd.ebp.vi;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;

import com.hgd.ebp.domain.User;

public class RenewUserBean {
	private int uid;
	private String userName;
	private String password;
	private String passwordagain;
	@NotEmpty(message="真实姓名不能为空")
	@Size(min=2,max=8,message="姓名长度在{min}~{max}之间")
	private String uname;
	@NotEmpty(message="性别不能为空")
	private String gender;
	@NotEmpty(message="身份证号不能为空")
	@Size(min=18,max=18,message="身份证号长度有误")
	private String idCard;
	private String address;
	private String telNo;
	private double balance;
	private String images;
	
	public RenewUserBean(int uid, String userName, String password,
			String passwordagain, String uname, String gender, String idCard,
			String address, String telNo, double balance, String images) {
		this.uid = uid;
		this.userName = userName;
		this.password = password;
		this.passwordagain = passwordagain;
		this.uname = uname;
		this.gender = gender;
		this.idCard = idCard;
		this.address = address;
		this.telNo = telNo;
		this.balance = balance;
		this.images = images;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public RenewUserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RenewUserBean(int uid, String username, String password,
			String passwordagain, String uname, String gender, String idcard,
			String address, String telno, double balance) {
		super();
		this.uid = uid;
		this.userName = username;
		this.password = password;
		this.passwordagain = passwordagain;
		this.uname = uname;
		this.gender = gender;
		this.idCard = idcard;
		this.address = address;
		this.telNo = telno;
		this.balance = balance;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the passwordagain
	 */
	public String getPasswordagain() {
		return passwordagain;
	}
	/**
	 * @param passwordagain the passwordagain to set
	 */
	public void setPasswordagain(String passwordagain) {
		this.passwordagain = passwordagain;
	}
	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the idcard
	 */
	public String getIdcard() {
		return idCard;
	}
	/**
	 * @param idcard the idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idCard = idcard;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the telno
	 */
	public String getTelno() {
		return telNo;
	}
	/**
	 * @param telno the telno to set
	 */
	public void setTelno(String telno) {
		this.telNo = telno;
	}
	
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return userName;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.userName = username;
	}
	
	
	
	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setBean(User user){
		this.uid = user.getUid();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.passwordagain = user.getPasswordagain();
		this.uname = user.getUname();
		this.gender = user.getGender();
		this.idCard = user.getIdCard();
		this.address = user.getAddress();
		this.telNo = user.getTelNo();
		this.balance = user.getBalance();
		this.images = user.getImages();
	}
}
