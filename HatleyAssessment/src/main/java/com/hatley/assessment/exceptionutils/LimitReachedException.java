package com.hatley.assessment.exceptionutils;

public class LimitReachedException extends RuntimeException {

	private static final long serialVersionUID = 5L;

	public LimitReachedException(String arg0) {
		super(arg0);
	}

}
