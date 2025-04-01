package com.example.demo.module.registration.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.module.registration.dto.RegistrationDto;

@Mapper
public interface RegistrationMapper {
	
	boolean registrationClass(RegistrationDto registrationDto);
}
