package com.example.demo.Exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;
	private String details;
	
	public ExceptionResponse(Date timestamp, String details, String message) {
		this.timestamp = timestamp;
		this.details = details;
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetails() {
		return details;
	}

	public String getMessage() {
		return message;
	}
	
}