package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Schema(description = "HTTP 상태 코드", example = "NOT FOUND")
	private HttpStatus code;

	@Schema(description = "exception code", example = "3000")
	private int code1;


	public CustomException(HttpStatus code){
		super();
		this.code = code;
	}


	public CustomException(String message){
		super(message);
		this.code = HttpStatus.INTERNAL_SERVER_ERROR;
	}


	public CustomException(HttpStatus code, String message){
		super(message);
		this.code = code;
		this.code1 = code.value();
	}

	
	public CustomException(HttpStatus code, String message , int code1){
		super(message);
		this.code = code;
		this.code1 = code1;
	}

	
	public HttpStatus getHttpStatus() {
		return code;
	}


}