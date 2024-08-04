package com.mouraforte.cadastro.service.exceptions;

public class ObjectNotFoundExeception extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExeception(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundExeception(String message) {
		super(message);
	}
	
	
}
