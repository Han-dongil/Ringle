package com.example.demo.module.classes.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ClassDto {
	
	@Schema(description = "수업번호" , example="1" , accessMode = Schema.AccessMode.READ_ONLY)
	Integer classNo;
	
	@Schema(description = "수업시작시간" , example="2025-03-30 15:00:00" )
	String startTime;
	
	@Schema(description = "수업종료시간" , example="2025-03-30 16:00:00")
	String endTime;
	
	@Schema(description = "튜터시퀀스넘버" , example="1"  , accessMode = Schema.AccessMode.READ_ONLY)
	Integer tutorNo;
	
	@Schema(description = "튜터아이디" , example="tutor" , accessMode = Schema.AccessMode.READ_ONLY)
	String tutorId;
	
	@Schema(description = "수업제목" , example="수업1")
	String className;
	
	@Schema(description = "수업길이" , example="60(분)"  , accessMode = Schema.AccessMode.READ_ONLY)
	Integer classTime;
	
	
}
