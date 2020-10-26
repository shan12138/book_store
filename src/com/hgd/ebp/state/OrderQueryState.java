package com.hgd.ebp.state;

public class OrderQueryState extends PageQueryState {
	private String begtime = "";
    private String endtime = "";
    private String name;
	private String idcard;
	private Integer oid;
	
    public OrderQueryState() {}

	public String getBegtime() {
		return begtime;
	}
	
	public void setBegtime(String begtime) {
		this.begtime = begtime;
	}

	@Override
	public String toString() {
		return "OrderTimeQueryState [begtime=" + begtime + ", endtime="
				+ endtime + ", name=" + name + ", idcard=" + idcard + ", oid="
				+ oid + "]";
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public OrderQueryState(int curPage, String begtime, String endtime) {
		setCurPage(curPage);
		this.begtime = begtime;
		this.endtime = endtime;
	}
	
	public OrderQueryState(int curPage, String name, Integer oid,String idcard) {
		setCurPage(curPage);
		this.idcard = idcard;
		this.oid = oid;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public OrderQueryState(String begtime, String endtime, String name,
			String idcard, Integer oid) {
		super();
		this.begtime = begtime;
		this.endtime = endtime;
		this.name = name;
		this.idcard = idcard;
		this.oid = oid;
	}
}
