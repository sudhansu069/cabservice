package com.target.cabservice.exceptions;

public class InvalidDropLocationException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDropLocationException(String message) {
      
		 super(message);
	}

	public InvalidDropLocationException() {
       super();
	}

}
