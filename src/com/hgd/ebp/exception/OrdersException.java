package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class OrdersException extends Exception {
	public OrdersException(Exception e) {
		super(e);
	}
	
	public OrdersException(String msg) {
		super(msg);
	}
}
