package com.hgd.ebp.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Admin {
	
	@NotEmpty(message="�û�������Ϊ��")
	private String name;
	@NotEmpty(message="���벻��Ϊ��")
	private String password;
	
	public Admin() {}

	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
