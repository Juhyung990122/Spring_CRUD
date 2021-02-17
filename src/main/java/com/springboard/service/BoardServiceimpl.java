package com.springboard.service;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.springboard.domain.Board;
import com.springboard.domain.User;
import com.springboard.persistence.BoardRepository;
import com.springboard.persistence.UserRepository;
import com.springboard.response.CommonResult;
import com.springboard.response.ListResult;
import com.springboard.response.SingleResult;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.minidev.json.JSONObject;

@Service("BoardService")
public class BoardServiceimpl<T> implements BoardService {
	
	@Autowired
	private BoardRepository BoardRepository;
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private ResponseService responseService;

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType="header")
		})
	public ListResult<Iterable<Board>> GetPost() {
		Iterable<Board> result =BoardRepository.findAll();
		return (ListResult<Iterable<Board>>)responseService.getListResult((List<T>)result);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType="header")
		})
	public CommonResult GetPostDetail(@PathVariable("id")Long id){
		Optional<Board> post = BoardRepository.findById(id);
		if (post.isPresent()) {
			return responseService.getSingleResult(post);
		}
		return responseService.getFailResult();
		
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType="header")
		})
	public SingleResult<Board> CreatePost(@RequestBody Board newpost, Authentication authentication) {
		Board post = new Board();
		User user =(User) authentication.getPrincipal();
		post.setUser(user);
		post.setTitle(newpost.getTitle());
		post.setContent(newpost.getContent()); 
		BoardRepository.save(post);
		return responseService.getSingleResult(post);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType="header")
		})
	public CommonResult EditPost(@RequestBody JSONObject editpost, @PathVariable("id")Long id, Authentication authentication) {
		Board post = BoardRepository.findById(id).get();
		if (authentication.getPrincipal().equals(post.getUser())) {
			String title = editpost.getOrDefault("title","").toString();
			String content = editpost.getOrDefault("content","").toString();
			
			if(title != "") {
				post.setTitle(title);
			}
			if(content != "") {
				post.setContent(content);
			}
			BoardRepository.save(post);
			return responseService.getSingleResult(post);
		}
		return responseService.getFailResult();
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", required = true, dataType = "String", paramType="header")
		})
	public CommonResult DeletePost(@PathVariable("id")Long id,Authentication authentication) {
		Optional<Board> post = BoardRepository.findById(id);
		if(post.isEmpty()) {
			return responseService.getFailResult();
		}
		if (authentication.getPrincipal().equals(post.get().getUser())) {
			BoardRepository.deleteById(id);
			return responseService.getSingleResult("success");
		}
		return responseService.getFailResult();
	}
	
}
