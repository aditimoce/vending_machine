package com.vendingmachine.exception;

public class NotFullPaidException extends RuntimeException {

	public NotFullPaidException(String message) {
		super(message);
	}

}
