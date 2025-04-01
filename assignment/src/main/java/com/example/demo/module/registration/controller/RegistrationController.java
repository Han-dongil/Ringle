package com.example.demo.module.registration.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.TutorDto;
import com.example.demo.module.members.service.MemberService;
import com.example.demo.module.registration.service.RegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

	private final MemberService memberService;
	
	private final RegistrationService registrationService;
	
	@Operation(summary = "시간대, 수업길이, 튜터로 새로운 수업 신청(학생)", description = "수업정보를 입력하여 수업을 신청함.")
	@PostMapping(value="/api/v1/student/registration")
	public boolean applicableTutor (
			@Parameter(name = "startTime", description = "시작시간 (시작날 , 종료날은 같아야함)", example = "2025-03-30 15:00:00") @RequestParam(name="startTime") String startTime
			,@Parameter(name = "endTime", description = "종료시간 (시작날 , 종료날은 같아야함) ", example = "2025-03-30 15:00:00") @RequestParam(name="endTime") String endTime
			,@Parameter(name = "classTime", description = "수업길이(분)", example = "60") @RequestParam(name="classTime") Integer classTime
			,@Parameter(name = "tutorId", description = "튜터의 id", example = "tutor") @RequestParam(name="tutorId") String tutorId
			, HttpServletRequest request) throws Exception{
		ClassDto classDto = new ClassDto();
		if(!startTime.split(" ")[0].equals(endTime.split(" ")[0]) )
			throw new CustomException( HttpStatus.BAD_REQUEST , "수업시작과 종료일은 같아야합니다.",4020 );
		if(startTime != null && !startTime.equals("") )
			classDto.setStartTime(startTime);
		if(endTime != null && !endTime.equals("") )
			classDto.setEndTime(endTime);
		if(classTime != null && classTime!=0 )
			classDto.setClassTime(classTime);
		if(tutorId != null && !tutorId.equals("") )
			classDto.setTutorId(tutorId);
		return registrationService.registrationClass(classDto , request);
	}
}
