package com.example.demo.module.members.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.TutorDto;
import com.example.demo.module.members.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@Operation(summary = "수업가능 튜터목록(학생)", description = "시간대 & 수업 길이로 수업 가능한 튜터 조회",
			responses = {
		            @ApiResponse(responseCode = "200", description = "성공"),
		            @ApiResponse(responseCode = "404", description = "입력값오류"),
    })
	@GetMapping(value="/api/v1/student/tutor")
	public List<TutorDto> applicableTutor (
			@Parameter(name = "startTime", description = "시작시간", example = "14:30:00") @RequestParam(name="startTime") String startTime
			,@Parameter(name = "endTime", description = "종료시간", example = "15:30:00") @RequestParam(name="endTime") String endTime
			,@Parameter(name = "classTime", description = "수업길이(분)", example = "60") @RequestParam(name="classTime") Integer classTime) throws Exception{
		ClassDto classDto = new ClassDto();
		if(startTime != null && !startTime.equals("") )
			classDto.setStartTime(startTime);
		if(endTime != null && !endTime.equals("") )
			classDto.setEndTime(endTime);
		if(classTime != null && classTime!=0 )
			classDto.setClassTime(classTime);
		return memberService.getTutor(classDto);
	}
}
