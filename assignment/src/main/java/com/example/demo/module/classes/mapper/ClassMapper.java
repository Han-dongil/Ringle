package com.example.demo.module.classes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.ResultClassDto;
import com.example.demo.module.members.dto.MemberDto;

@Mapper
public interface ClassMapper {
	

	boolean createClass(ClassDto classDto);
	
	List<ClassDto> availableTime(ClassDto classDto);
	
	boolean deleteClassByClassNo(Integer classNo);
	
	List<ResultClassDto> classByStudentId(MemberDto memberDto);
}
