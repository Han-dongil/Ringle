package com.example.demo.module.classes.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class TutorDto {
	
	Integer classNo;
	
	String tutorId;
	
	String tutorName;
	
	String startTime;
	
	Integer classTime;
	
	String endTime;
	
	String className;
	
}
