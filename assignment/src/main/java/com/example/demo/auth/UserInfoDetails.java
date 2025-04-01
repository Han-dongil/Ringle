package com.example.demo.auth;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetails implements UserDetails  {

    private String username;
    private String password;
    private Integer memberNo;
    private List<GrantedAuthority> authorities;

 // 생성자
    public UserInfoDetails(String username, String password, List<GrantedAuthority> roles , Integer memberNo) {
        this.username = username;
        this.password = password;
        this.authorities = roles;
        this.memberNo = memberNo;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;  // 권한을 반환
    }

    public String getPassword() {
        return password;  // 비밀번호 반환
    }

    public String getUsername() {
        return username;  // 사용자명 반환
    }

    public boolean isAccountNonExpired() {
        return true;  // 계정 만료 여부
    }
    
    public boolean isAccountNonLocked() {
        return true;  // 계정 잠금 여부
    }

    public boolean isCredentialsNonExpired() {
        return true;  // 자격 증명 만료 여부
    }

    public boolean isEnabled() {
        return true;  // 계정 활성화 여부
    }

    // 추가적으로 필요한 사용자 정보를 여기에 추가할 수 있음
}
