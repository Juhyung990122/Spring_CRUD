package com.springboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboard.config.security.JwtTokenProvider;
import com.springboard.domain.User;
import com.springboard.persistence.UserRepository;
import com.springboard.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;

@RequiredArgsConstructor
@RestController
public class UserController {
	@Autowired
	public CustomUserDetailService userService;
	private final JwtTokenProvider jwtTokenProvider;
	
	@PostMapping(value = "/registration")
	public String signup(@RequestBody JSONObject user) {
		return userService.signup(user);
	}
	
	@PostMapping(value="/login")
	public String login(@RequestBody JSONObject user) {
		return userService.login(user, jwtTokenProvider);
	}
}
