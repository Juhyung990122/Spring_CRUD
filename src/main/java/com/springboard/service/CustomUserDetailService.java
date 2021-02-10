package com.springboard.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.config.security.JwtTokenProvider;
import com.springboard.domain.User;
import com.springboard.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	public UserRepository UserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
	}
	
	
	public String signup(JSONObject user) {
		UserRepository.save(User.builder()
                .email(user.get("email").toString())
                // spring security 5부터 passwordEncoder쓰려면 password앞에 null붙여야함
                .password(user.get("password").toString())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getUid();
		return "Created";
	}
	
	public String login(@RequestBody JSONObject user, JwtTokenProvider jwtTokenProvider) {
		User loginuser = UserRepository.findByEmail(user.get("email").toString()).orElse(null);
		return jwtTokenProvider.createToken(loginuser.getUsername(), loginuser.getRoles());
}
}
