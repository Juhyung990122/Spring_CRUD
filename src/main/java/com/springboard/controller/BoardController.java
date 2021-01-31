package com.springboard.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {
	@GetMapping("/")
	public String index() {
		return "Index page";
	}
}
