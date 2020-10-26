package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class BookException extends Exception {
	public 	BookException(Exception e){
		super(e);
	}

	public 	BookException(String msg){
		super(msg);
	}
}
