package com.example.demo.Exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMathOperationException extends RuntimeException implements Serializable{

	public UnsuportedMathOperationException(String ex) {
		super(ex);
	}

	private static final long serialVersionUID = 1L;
	
}
