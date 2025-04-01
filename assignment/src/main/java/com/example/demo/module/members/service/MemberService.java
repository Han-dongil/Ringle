package com.example.demo.module.members.service;

import java.util.List;

import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.TutorDto;
import com.example.demo.module.members.dto.MemberDto;

public interface MemberService {

	List<TutorDto> getTutor(ClassDto classDto) throws Exception;
	
	MemberDto getMember(MemberDto memberDto) throws Exception;
}
