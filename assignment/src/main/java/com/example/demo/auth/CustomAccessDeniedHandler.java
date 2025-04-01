package com.example.demo.auth;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorAttribute;
import com.example.demo.util.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    	System.err.println("디나이드핸들러");
	 	CustomException customException = new CustomException(HttpStatus.FORBIDDEN, "권한을 확인해주세요.");
        
        CustomResponse<ErrorAttribute> customResponse = new CustomResponse<>(  
															        		ErrorAttribute.builder()
																			.code(customException.getCode() )
																			.message(customException.getMessage())
																			.code1( customException.getCode1() )
																			.build(),HttpStatus.FORBIDDEN);
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        String jsonResponse = objectMapper.writeValueAsString(ErrorAttribute.builder()
															.code(customException.getCode() )
															.message(customException.getMessage())
															.code1( customException.getCode1() )
															.build());
        
        response.getWriter().write(jsonResponse);
    }
    	
}
