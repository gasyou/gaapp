package com.gasyou.gam.common.service;

public class ServiceException extends RuntimeException {

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
