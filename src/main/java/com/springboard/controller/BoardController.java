package com.springboard.controller;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboard.domain.Board;
import com.springboard.persistence.BoardRepository;
import com.springboard.service.BoardServiceimpl;


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
	
	@PutMapping("/post")
	public Board Create(@RequestBody Board newpost) {
		return BoardService.CreatePost(newpost);
	}
	
}
