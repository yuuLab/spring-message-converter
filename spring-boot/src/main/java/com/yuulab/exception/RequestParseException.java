package com.yuulab.exception;

public class RequestParseException extends Exception {

	public RequestParseException(String message) {
		super(message);
	}

	public RequestParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestParseException(Throwable cause) {
		super(cause);
	}
}
