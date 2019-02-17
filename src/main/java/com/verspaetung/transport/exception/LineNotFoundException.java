package com.verspaetung.transport.exception;


public class LineNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public LineNotFoundException(String params) {
		super("Could not find employee with search criteria: " + params);
	}
}