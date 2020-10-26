package com.hgd.ebp.domain;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CommentBean {
	int uid;
	String username;
	int bid;
	@NotEmpty(message="评论不能为空")
	@Length(max=300,message ="评论应在300字以内")
	private String comments;
	private Timestamp commentTime;
	
	public CommentBean() {
		super();
	}

	public CommentBean(int uid, int bid, String comments, Timestamp commentTime,String username) {
		super();
		this.uid = uid;
		this.bid = bid;
		this.comments = comments;
		this.commentTime = commentTime;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}
	
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


}
