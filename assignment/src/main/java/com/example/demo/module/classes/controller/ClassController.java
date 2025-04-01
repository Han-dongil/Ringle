package com.example.demo.module.classes.controller;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.module.classes.dto.ClassDto;
import com.example.demo.module.classes.dto.ResultClassDto;
import com.example.demo.module.classes.service.ClassService;
import com.example.demo.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClassController {

	private final ClassService classService;
	
	@Operation(summary = "수업생성(튜터)", description = "튜터가 수업생성",            
			responses = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "입력값 오류" )
    })
	@PostMapping("/api/v1/tutor/classes")
	public boolean createClass(@RequestBody ClassDto classDto ,HttpServletRequest request) throws Exception{
		classDto.getStartTime();
		classDto.getEndTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime t1 = LocalTime.parse(classDto.getStartTime().split(" ")[1], formatter);
        LocalTime t2 = LocalTime.parse(classDto.getEndTime().split(" ")[1], formatter);
        Duration duration = Duration.between(t1, t2);
        System.err.println(classDto.getStartTime().split(" ")[0] );
        System.err.println(classDto.getEndTime().split(" ")[0] );
        if(duration.toMinutes() != 30 && duration.toMinutes() !=60 || !classDto.getStartTime().split(" ")[0].equals( classDto.getEndTime().split(" ")[0] ) )
        	throw new CustomException(HttpStatus.BAD_REQUEST,"수업시간은 30분 혹은 60분으로만 설정 가능합니다.",4000);
		System.err.println("수업생성");
		JwtUtil jwtTokenUtils = new JwtUtil();
		String token = jwtTokenUtils.getToken(request);
		if(token!=null)
			classDto.setTutorId(jwtTokenUtils.getUsername(token));
		
		classDto.setTutorId(jwtTokenUtils.getUsername(token));
		return classService.createClass(classDto);
	}
	
	@Operation(summary = "생성한 수업조회(튜터)", description = "튜터가 자기가 생성한 수업을 조회",
			responses = {
		            @ApiResponse(responseCode = "200", description = "성공"),
    })
	@GetMapping(value="/api/v1/tutor/classes")
	public List<ClassDto> tutorClass(HttpServletRequest request) throws Exception{
		ClassDto classDto = new ClassDto();
		JwtUtil jwtTokenUtils = new JwtUtil();
		String token = jwtTokenUtils.getToken(request);
		if(token!=null)
			classDto.setTutorId(jwtTokenUtils.getUsername(token));
		return classService.availableTime(classDto);
	}

	@Operation(summary = "수업삭제하기(튜터)", description = "수업삭제",
			responses = {
		            @ApiResponse(responseCode = "200", description = "성공"),
		            @ApiResponse(responseCode = "404", description = "삭제할데이터 없음"),
    })
	@DeleteMapping(value="/api/v1/tutor/classes/{classNo}")
	public boolean deleteClass(@Parameter(name = "classNo", description = "수업번호", example = "1") @PathVariable(name="classNo") Integer classNo,HttpServletRequest request) throws Exception{
		JwtUtil jwtTokenUtils = new JwtUtil();
		String token = jwtTokenUtils.getToken(request);
		
		if(token!=null&& classService.deleteClassByClassNo(classNo)) {
			return true;
		}
		throw new CustomException(HttpStatus.NOT_FOUND,"해당 수업이 존재하지 않습니다.",4010);
		
	}
	
	@Operation(summary = "수업가능 시간대 조회(학생)", description = "기간 & 수업 길이로 현재 수업 가능한 시간대를 조회",
			responses = {
		            @ApiResponse(responseCode = "200", description = "성공"),
		            @ApiResponse(responseCode = "404", description = "입력값오류"),
    })
	@GetMapping(value="/api/v1/student/classes")
	public List<ClassDto> applicableClass(
			@Parameter(name = "startTime", description = "시작날짜", example = "2025-03-30") @RequestParam(name="startTime") String startTime
			,@Parameter(name = "endTime", description = "종료날짜", example = "2025-04-30") @RequestParam(name="endTime") String endTime
			,@Parameter(name = "classTime", description = "수업길이(분)", example = "60") @RequestParam(name="classTime") Integer classTime
			) throws Exception{
		ClassDto classDto = new ClassDto();
		if(startTime != null && !startTime.equals("") )
			classDto.setStartTime(startTime);
		if(endTime != null && !endTime.equals("") )
			classDto.setEndTime(endTime);
		if(startTime != null && !startTime.equals("") && startTime.split(" ").length==1 )
			classDto.setStartTime(startTime.split(" ")[0]+" "+"00:00:00");
		if(endTime != null && !endTime.equals("")  && endTime.split(" ").length==1 )
			classDto.setEndTime(endTime.split(" ")[0]+" "+"23:59:59");
		if(classTime != null && classTime!=0 )
			classDto.setClassTime(classTime);
		return classService.availableTime(classDto);
	}
	
	@Operation(summary = "내수업조회(학생)", description = "신청한 수업 조회")
	@GetMapping(value="/api/v1/student/classes/my")
	public List<ResultClassDto> myClasses(HttpServletRequest request) throws Exception{
		return classService.classByStudentId(request);
	}
//	@GetMapping(value="/api/v1/student/me/classes")
//	public List<ClassDto> applicableClass(HttpServletRequest request 
//			) throws Exception{
//		ClassDto classDto = new ClassDto();
//		JwtUtil jwtTokenUtils = new JwtUtil();
//		String token = jwtTokenUtils.getToken(request);
//		if(token!=null)
//			classDto.setTutorId(jwtTokenUtils.getUsername(token));
//		return classService.availableTime(classDto);
//	}
}
