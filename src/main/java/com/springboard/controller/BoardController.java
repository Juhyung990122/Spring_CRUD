package com.springboard.controller;
import java.util.List;

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

}
