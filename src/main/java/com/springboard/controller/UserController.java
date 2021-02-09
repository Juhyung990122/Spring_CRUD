package com.springboard.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboard.config.security.JwtTokenProvider;
import com.springboard.domain.User;
import com.springboard.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;

@RequiredArgsConstructor
@RestController
public class UserController {
	@Autowired
	public UserRepository UserRepository;
	public PasswordEncoder passwordEncoder;
	public JwtTokenProvider jwtTokenProvider;
	
	@PostMapping(value = "/registration")
	public String signup(@RequestBody JSONObject user) {
		System.out.println((CharSequence) user.getAsString("password").toString());
		return UserRepository.save(User.builder()
                .email(user.get("email").toString())
                .password(passwordEncoder.encode((CharSequence) user.getAsString("password").toString()))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getUid();
	}
	
	@PostMapping(value="/login")
	public String login(@RequestBody JSONObject user) {
		User loginuser = UserRepository.findByEmail(user.getAsString("email")).orElse(null);
		return jwtTokenProvider.createToken(loginuser.getUsername(), loginuser.getRoles());
	}
}
