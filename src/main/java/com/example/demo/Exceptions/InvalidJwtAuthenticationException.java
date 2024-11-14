package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public InvalidJwtAuthenticationException(String ex) {
		super(ex);
	}
	public InvalidJwtAuthenticationException() {
		super("It is not allowed to persist a null object");
	}
	
}
