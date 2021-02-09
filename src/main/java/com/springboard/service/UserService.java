package com.springboard.service;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.minidev.json.JSONObject;

public interface UserService {
	public String signup(@RequestBody JSONObject user) ;
	public String login(@RequestBody JSONObject user); 
}
