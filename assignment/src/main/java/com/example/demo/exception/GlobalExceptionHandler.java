package com.example.demo.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.util.CustomResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


	@ExceptionHandler(CustomException.class)
	protected CustomResponse<ErrorAttribute> handleBindException(
			CustomException customException,
			HttpServletRequest request,
			HttpServletResponse response,
			@Nullable Object handler) throws IOException {
		System.err.println("익셉션");
		ErrorAttribute err = ErrorAttribute.builder()
				.code(customException.getCode() )
				.message(customException.getMessage())
				.code1( customException.getCode1() )
				.build();
		return new CustomResponse<>(err, customException.getCode());

	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected CustomResponse<ErrorAttribute> MissingParameterException(
			HttpServletRequest request,
			HttpServletResponse response,
			MissingServletRequestParameterException ex,
			@Nullable Object handler) throws IOException {
		System.err.println("익셉션");
		ErrorAttribute err = ErrorAttribute.builder()
				.code(HttpStatus.BAD_REQUEST )
				.message(ex.getParameterName() +" 필드가 누락되었습니다.")
				.fieldName(ex.getParameterName())
				.code1( 400 )
				.build();
		return new CustomResponse<>(err, HttpStatus.BAD_REQUEST);
		
	}
	

}
