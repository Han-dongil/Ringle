package com.example.demo.module.members.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.TutorDto;
import com.example.demo.module.members.dto.MemberDto;
import com.example.demo.module.members.mapper.MembersMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MembersMapper memberMapper;

	@Override
	public List<TutorDto> getTutor(ClassDto classDto) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.getTutor(classDto);
	}

	@Override
	public MemberDto getMember(MemberDto memberDto) throws Exception {
		return memberMapper.getMember(memberDto);
	}
	
	

}
