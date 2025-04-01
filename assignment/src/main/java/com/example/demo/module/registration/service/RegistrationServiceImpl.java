package com.example.demo.module.registration.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomException;
import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.service.ClassService;
import com.example.demo.module.members.dto.MemberDto;
import com.example.demo.module.members.service.MemberService;
import com.example.demo.module.registration.dto.RegistrationDto;
import com.example.demo.module.registration.mapper.RegistrationMapper;
import com.example.demo.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

	private final ClassService classService;
	
	private final MemberService memberService;
	
	private final RegistrationMapper registrationMapper;

	@Override
	public boolean registrationClass(ClassDto classDto , HttpServletRequest request) throws Exception {
		JwtUtil jwtTokenUtils = new JwtUtil();
		String token = jwtTokenUtils.getToken(request);
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(jwtTokenUtils.getUsername(token));
		//수강신청 시간겹칠떄 예외처리 추가
		
		//해당조건에 맞는 수업번호찾기
		List<ClassDto> list=classService.availableTime(classDto);
		RegistrationDto registrationDto = new RegistrationDto();
		if(list.size()==1) { // 선택한 시간과 , 튜터id ,수업길이로 조회했는데 조회값이 1개이상이면 필터값이 잘못되었음 -> 동일한 튜터가 같은시간대에 수업이 겹친다는 뜻이기 떄문,
			registrationDto.setClassNo(list.get(0).getClassNo());
			registrationDto.setStudentNo(memberService.getMember(memberDto).getMemberNo());
		}
		else
			throw new CustomException(HttpStatus.BAD_REQUEST,"입력값이 잘못되었습니다.");
		boolean result = false;
		try {
			result = registrationMapper.registrationClass(registrationDto);
		} catch (DuplicateKeyException e) {
			throw new CustomException(HttpStatus.BAD_REQUEST,"이미 수강신청한 수업입니다.");
		}
		
		
		return result;
	}

	

}
