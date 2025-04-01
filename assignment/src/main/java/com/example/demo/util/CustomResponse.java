package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse<T> extends ResponseEntity<T> {
	
	public CustomResponse(HttpStatus status) {
		super(status);
	}

	public CustomResponse() {
		super(HttpStatus.OK);
	}


	public CustomResponse(T body) {
		super(body, HttpStatus.OK);
	}


	public CustomResponse(T body, HttpStatus status) {
		super(body, status);

	}

}
