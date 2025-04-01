package com.example.demo.module.registration.service;

import com.example.demo.module.classes.dto.ClassDto;

import jakarta.servlet.http.HttpServletRequest;

public interface RegistrationService {

	public boolean registrationClass(ClassDto classDto , HttpServletRequest request) throws Exception;
}
