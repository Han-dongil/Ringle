package com.example.demo.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomException;
import com.example.demo.module.members.dto.MemberDto;
import com.example.demo.module.members.mapper.MembersMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final MembersMapper membersMapper;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	MemberDto dto = new MemberDto();
    	dto.setMemberId(username);
    	dto=membersMapper.getMember(dto);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + dto.getRole()) );
        System.err.println(dto.getRole());
    	UserInfoDetails user = new UserInfoDetails(dto.getMemberId() , dto.getPassword() , authorities , dto.getMemberNo());
    	return user;
    }
}
 