package com.springboard.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.config.security.JwtTokenProvider;

import net.minidev.json.JSONObject;

public interface CustomUserDetailService {
	public String signup(JSONObject user, PasswordEncoder passwordEncoder);
	public String login(@RequestBody JSONObject user, JwtTokenProvider jwtTokenProvider);
	public String leave(Long uid);
}
