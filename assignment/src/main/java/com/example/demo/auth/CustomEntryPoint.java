package com.example.demo.auth;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorAttribute;
import com.example.demo.util.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint{

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
			System.err.println("엔트리포인트");
		 	CustomException customException = new CustomException(HttpStatus.UNAUTHORIZED, "로그인을 확인해주세요.");
	        
	        CustomResponse<ErrorAttribute> customResponse = new CustomResponse<>(  
																        		ErrorAttribute.builder()
																				.code(customException.getCode() )
																				.message(customException.getMessage())
																				.code1( customException.getCode1() )
																				.build(),HttpStatus.UNAUTHORIZED);
	        ObjectMapper objectMapper = new ObjectMapper();
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        
	        String jsonResponse = objectMapper.writeValueAsString(ErrorAttribute.builder()
																.code(customException.getCode() )
																.message(customException.getMessage())
																.code1( customException.getCode1() )
																.build());
	        
	        response.getWriter().write(jsonResponse);
	}

}
