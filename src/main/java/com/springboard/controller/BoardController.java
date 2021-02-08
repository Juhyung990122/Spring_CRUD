package com.springboard.controller;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboard.domain.Board;

import com.springboard.service.BoardServiceimpl;

import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;


@RestController
public class BoardController {
	
	@Autowired
	public BoardServiceimpl BoardService;
	
	@ApiOperation(value="전체 포스트" ,notes = "전체 포스트를 조회한다.")
	@GetMapping("/")
	public Iterable<Board> Main() {
		
		return BoardService.GetPost();
	}
	
	@ApiOperation(value = "포스트 디테일 ",notes = "포스트 세부내용 조회한다.")
	@GetMapping("/post/{id}")
	public Optional<Board> Detail(@PathVariable(value = "id")Long id){
		return BoardService.GetPostDetail(id);
	}
	
	@ApiOperation(value = "포스트 생성 ",notes = "새로운 포스트를 생성한다.")
	@PostMapping("/post")
	public Board Create(@RequestBody Board newpost) {
		return BoardService.CreatePost(newpost);
	}
	
	@ApiOperation(value = "포스트 수정 ",notes = "포스트를 수정한다.")
	@PutMapping("/post/{id}")
	public Board Edit(@RequestBody JSONObject editpost, @PathVariable("id")Long id){
		return BoardService.EditPost(editpost,id);
	}
	@ApiOperation(value="포스트 삭제", notes = "포스트를 삭제한다.")
	@DeleteMapping("/post/{id}")
	public String Delete(@PathVariable("id")Long id) {
		return BoardService.DeletePost(id);
	}
	
}
