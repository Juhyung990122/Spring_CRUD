package com.springboard.controller;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboard.domain.Board;

import com.springboard.service.BoardServiceimpl;

import net.minidev.json.JSONObject;


@RestController
public class BoardController {
	
	@Autowired
	public BoardServiceimpl BoardService;
	
	@GetMapping("/")
	public Iterable<Board> Main() {
		
		return BoardService.GetPost();
	}

	@GetMapping("/post/{id}")
	public Optional<Board> Detail(@PathVariable(value = "id")Long id){
		return BoardService.GetPostDetail(id);
	}
	
	@PostMapping("/post")
	public Board Create(@RequestBody Board newpost) {
		return BoardService.CreatePost(newpost);
	}
	
	@PutMapping("/post/{id}")
	public Board Edit(@RequestBody JSONObject editpost, @PathVariable("id")Long id){
		return BoardService.EditPost(editpost,id);
	}
	
}
