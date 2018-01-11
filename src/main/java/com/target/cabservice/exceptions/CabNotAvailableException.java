package com.target.cabservice.exceptions;

public class CabNotAvailableException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CabNotAvailableException(String message) {
		
		
		  super(message);
	}
	
	public CabNotAvailableException() {
		
		
		  super();
	}

}
