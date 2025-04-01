package com.example.demo.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.module.members.dto.MemberDto;
import com.example.demo.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "User", description = "Operations related to users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Operation(
            summary = "Get user by ID",
            description = "Fetch user details by providing user ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "유저정보오류" )
            }
    )
	@PostMapping(value="/api/v1/login")
	public String login (@RequestBody MemberDto dto) throws Exception{
		System.err.println(dto);
		System.err.println("!!!!@");
		
		System.err.println(encoder.matches("1234", "$2a$12$mefwQCYkUao.ueirnWb6xuZzEFfAFK7.Pt8ddjE27BbDvAJQC3t4G"));
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getMemberId(), dto.getPassword()));
		} catch (Exception e) {
			throw new CustomException(HttpStatus.NOT_FOUND,"유저정보가 잘못되었습니다.");
		}
        return jwtUtil.generateToken(dto.getMemberId());
	}
	
	
}
