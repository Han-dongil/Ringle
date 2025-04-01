package com.example.demo.module.members.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class MemberDto {
	
	@Schema( description =  "회원 시퀀스번호" ,accessMode = Schema.AccessMode.READ_WRITE)
	@JsonIgnore
	Integer memberNo;
	
	@Schema( description =  "회원이름" , accessMode = Schema.AccessMode.READ_WRITE)
	@JsonIgnore
	String memberName;
	
	@Schema(description = "아이디" , example="tutor")
	String memberId;
	
	@Schema( description =  "jwt 토큰값" , accessMode = Schema.AccessMode.READ_WRITE)
	@JsonIgnore
	String token;
	
	@Schema(description = "패스워드" , example="1234")
	String password;
	
	@Schema( description =  "회원 권한" , accessMode = Schema.AccessMode.READ_WRITE)
	@JsonIgnore
	String role;
	
}
