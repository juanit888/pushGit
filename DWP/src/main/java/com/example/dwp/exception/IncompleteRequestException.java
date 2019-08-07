package com.example.dwp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompleteRequestException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public IncompleteRequestException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
