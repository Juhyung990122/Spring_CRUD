package com.springboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboard.config.security.JwtTokenProvider;

import com.springboard.service.CustomUserDetailServiceimpl;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;

@RequiredArgsConstructor
@RestController
public class UserController {
	@Autowired
	public CustomUserDetailServiceimpl userService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/registration")
	public String signup(@RequestBody JSONObject user) {
		return userService.signup(user,passwordEncoder);
	}
	
	@PostMapping(value="/login")
	public String login(@RequestBody JSONObject user) {
		return userService.login(user, jwtTokenProvider);
	}
	
	@DeleteMapping(value="/leave/{uid}")
	public String leave(@PathVariable("uid")Long uid) {
		return userService.leave(uid);
	}
}
