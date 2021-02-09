package com.springboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboard.config.security.JwtTokenProvider;
import com.springboard.domain.User;
import com.springboard.persistence.UserRepository;

import net.minidev.json.JSONObject;
import java.util.*;

public class UserServiceimpl implements UserService {
	
	@Autowired
	public UserRepository UserRepository;
	public PasswordEncoder passwordEncoder;
	public JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String signup(JSONObject user) {
		return UserRepository.save(User.builder()
                .email(user.get("email").toString())
                .password(passwordEncoder.encode((CharSequence) user.get("password").toString()))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getUid();
	}

	@Override
	public String login(JSONObject user) {
		User loginuser = UserRepository.findByEmail(user.get("email").toString()).orElse(null);
		return jwtTokenProvider.createToken(loginuser.getUsername(), loginuser.getRoles());
	}

}
