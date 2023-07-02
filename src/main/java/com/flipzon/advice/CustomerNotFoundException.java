package com.flipzon.advice;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
