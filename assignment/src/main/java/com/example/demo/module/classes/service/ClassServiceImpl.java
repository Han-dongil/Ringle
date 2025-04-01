package com.example.demo.module.classes.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomException;
import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.ResultClassDto;
import com.example.demo.module.classes.mapper.ClassMapper;
import com.example.demo.module.members.dto.MemberDto;
import com.example.demo.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService{

	private final ClassMapper classMapper;
	
	@Override
	public boolean createClass(ClassDto classDto) throws Exception {
		
		if(classMapper.availableTime(classDto).size()>0){
			throw new CustomException(HttpStatus.BAD_REQUEST,"기존수업시간과 중복되어 수업등록이 불가능합니다",3000);
		}
		return classMapper.createClass(classDto);
	}

	@Override
	public List<ClassDto> availableTime(ClassDto classDto) throws Exception {
		return classMapper.availableTime(classDto);
	}

	@Override
	public boolean deleteClassByClassNo(Integer classNo) throws Exception {
		// TODO Auto-generated method stub
		return classMapper.deleteClassByClassNo(classNo);
	}

	@Override
	public List<ResultClassDto> classByStudentId(HttpServletRequest request) throws Exception {
		JwtUtil jwtTokenUtils = new JwtUtil();
		String token = jwtTokenUtils.getToken(request);
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(jwtTokenUtils.getUsername(token));
		return classMapper.classByStudentId(memberDto);
	}
	
	

}
