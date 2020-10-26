package com.hgd.ebp.vi;


public class ChargeBean {
	private String uname;
	private String chargestyle;
	private Double chargemoney;
	private Double surplusmoney;
	
	public ChargeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChargeBean(String uname, String chargestyle, double chargemoney,
			double surplusmoney) {
		super();
		this.uname = uname;
		this.chargestyle = chargestyle;
		this.chargemoney = chargemoney;
		this.surplusmoney = surplusmoney;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getChargestyle() {
		return chargestyle;
	}

	public void setChargestyle(String chargestyle) {
		this.chargestyle = chargestyle;
	}

	public Double getChargemoney() {
		return chargemoney;
	}

	public void setChargemoney(Double chargemoney) {
		this.chargemoney = chargemoney;
	}

	public Double getSurplusmoney() {
		return surplusmoney;
	}

	public void setSurplusmoney(Double surplusmoney) {
		this.surplusmoney = surplusmoney;
	}
	
	
}
