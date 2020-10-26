package com.hgd.ebp.vi;

import org.hibernate.validator.constraints.NotEmpty;

public class UserBean {
	@NotEmpty(message="�û�������Ϊ��")
	private String userName;
	@NotEmpty(message="���벻��Ϊ��")
	private String upassword;
	
	public UserBean(){}
	
	public UserBean(String userName, String upassword) {
		super();
		this.userName = userName;
		this.upassword = upassword;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	
	
}
