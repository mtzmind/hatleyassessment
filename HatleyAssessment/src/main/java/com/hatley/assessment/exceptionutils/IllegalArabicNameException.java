package com.hatley.assessment.exceptionutils;

public class IllegalArabicNameException extends RuntimeException {

	public IllegalArabicNameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public IllegalArabicNameException(String arg0) {
		super(arg0);
		
	}

}
