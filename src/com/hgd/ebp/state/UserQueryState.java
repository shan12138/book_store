package com.hgd.ebp.state;

public class UserQueryState extends PageQueryState {
	private String startTime;
	private String endTime;
	private String name;
	private String idCard;
	private String telNo;
	private Integer status;
	
	public UserQueryState(){
		super();
		status = 0;
	}

	public UserQueryState(String startTime, String endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public UserQueryState(String name,String idCard, String telNo) {
		super();
		this.name = name;
		this.idCard = idCard;
		this.telNo = telNo;
	}

	
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public UserQueryState(String startTime, String endTime, String name,
			String idCard, String telNo, Integer status) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.idCard = idCard;
		this.telNo = telNo;
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public UserQueryState(String startTime, String endTime, String name,
			String idCard, String telNo, int status) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.idCard = idCard;
		this.telNo = telNo;
		this.status = status;
	}

	@Override
	public String toString() {
		return "" ;
	}
	
	
	
	
	
}
