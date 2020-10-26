package com.hgd.ebp.state;

public class TicketQueryState extends PageQueryState{
	private String startTime = "";
	private String endtime = "";
	private String begintime = "";
	
	public TicketQueryState() {} 
	
	public TicketQueryState(int curPage,String begintime, String endtime) {
		setCurPage(curPage);
		this.begintime = begintime;
		this.endtime = endtime;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public TicketQueryState(int curPage,String startTime){
		setCurPage(curPage);
		this.startTime=startTime;
	}
	
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public String getStartTime(){
		return startTime;
	}
	
	public void setStartTime(String startTime){
		this.startTime=startTime;
		
	}
	
	@Override
	public String toString() {
		return "TicketQueryState [startTime=" + startTime + ", endtime="
				+ endtime + ", begintime=" + begintime + "]";
	}
				
	
	
	
	
	
	
	
}
