package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorAttribute {

//	HttpStatus code;

	HttpStatus code;

	String message;

	String fieldName;

	int code1;

}

