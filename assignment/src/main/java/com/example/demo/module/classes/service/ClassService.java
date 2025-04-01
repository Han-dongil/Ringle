package com.example.demo.module.classes.service;

import java.util.List;

import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.ResultClassDto;
import com.example.demo.module.members.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

public interface ClassService {

	boolean createClass(ClassDto classDto) throws Exception;
	
	List<ClassDto> availableTime(ClassDto classDto) throws Exception;
	
	boolean deleteClassByClassNo(Integer classNo) throws Exception;
	
	List<ResultClassDto> classByStudentId(HttpServletRequest request) throws Exception;
	
}
