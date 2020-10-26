package com.hgd.ebp.exception;

public class EBPexception extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EBPexception(Exception e) {
		super(e);
	}
	
	public EBPexception(String msg) {
		super(msg);
	}
}
