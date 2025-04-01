package com.example.demo.module.registration.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class RegistrationDto {
	
	@ApiModelProperty(hidden = true)
	Integer registrationNo;
	
	Integer studentNo;
	
	Integer classNo;
	
	
}
