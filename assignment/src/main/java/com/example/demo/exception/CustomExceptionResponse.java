package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CustomExceptionResponse  {

	@Schema(description = "HTTP 상태 코드", example = "NOT FOUND")
	private HttpStatus code;

	@Schema(description = "exception code", example = "3000")
	private int code1;

	ErrorAttribute err;

	public CustomExceptionResponse(HttpStatus status) {
		this.code = status;
	}

	public CustomExceptionResponse() {
	}


	public CustomExceptionResponse(ErrorAttribute body) {
		this.err = body;
	}


	public CustomExceptionResponse(ErrorAttribute body, HttpStatus status , int code1) {
		this.err = body;
		this.code = status;
		this.code1 = code1;
	}


}