package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class CommentException  extends Exception{
	public CommentException(Exception e){
		super(e);
	}

	public CommentException(String msg){
		super(msg);
	}

}
