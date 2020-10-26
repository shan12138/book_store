package com.hgd.ebp.state;

public class BookQueryState extends PageQueryState{
	private String bname = "";
	private String author= "";
	private String keyWord = "";
	private String begintime="";
	private String endtime="";
	
	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public BookQueryState(String bname, String author, String keyWord,
			String begintime, String endtime) {
		super();
		this.bname = bname;
		this.author = author;
		this.keyWord = keyWord;
		this.begintime = begintime;
		this.endtime = endtime;
	}

	public BookQueryState(int curPage,String begintime, String endtime,String keyWord) {
		setCurPage(curPage);
		this.setBegintime(begintime);
		this.setEndtime(endtime);
		this.keyWord=keyWord;
	}

	public BookQueryState(int curPage,String keyWord) {
		setCurPage(curPage);
		this.keyWord=keyWord;
	}
	
	public BookQueryState() {}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public BookQueryState(String bname, String author) {
		super();
		this.bname = bname;
		this.author = author;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	} 
	
	@Override
	public String toString() {
		return "BookQueryState [bname=" + bname + ", author="
				+ author + ", keyWord="+ keyWord + "]";
	}
}
