package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.module.members.dto.MemberDto;
import com.example.demo.module.members.mapper.MembersMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainViewController {

	private final MembersMapper mapper;
	
	@GetMapping(value="/")
	public String main() {
		System.err.println("???!!");
		MemberDto dto = new MemberDto();
		dto.setMemberId("hdo6513");
		System.err.println(mapper.getMember(dto)); 
		
		return "redirect:/swagger-ui/index.html?urls.primaryName=%EC%A0%84%EC%B2%B4+API";
	}
}
