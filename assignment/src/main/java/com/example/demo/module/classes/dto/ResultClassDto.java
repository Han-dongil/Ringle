package com.example.demo.module.classes.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ResultClassDto {
	
	
	String className;
	String tutorName; 
	String startTime;
	String endTime;
	String classTime;
	
}
