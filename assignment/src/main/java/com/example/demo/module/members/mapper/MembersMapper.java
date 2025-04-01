package com.example.demo.module.members.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.TutorDto;
import com.example.demo.module.members.dto.MemberDto;

@Mapper
public interface MembersMapper {
	
	MemberDto getMember(MemberDto memberDto);
	
	List<TutorDto> getTutor(ClassDto classDto);

}
