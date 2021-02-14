package com.springboard.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.domain.Board;
import com.springboard.domain.User;
import com.springboard.persistence.BoardRepository;
import com.springboard.persistence.UserRepository;
import com.springboard.response.CommonResult;
import com.springboard.response.ListResult;
import com.springboard.response.SingleResult;

import net.minidev.json.JSONObject;

@Service("BoardService")
public class BoardServiceimpl<T> implements BoardService {
	
	@Autowired
	private BoardRepository BoardRepository;
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private ResponseService responseService;

	public ListResult<Iterable<Board>> GetPost() {
		Iterable<Board> result =BoardRepository.findAll();
		return (ListResult<Iterable<Board>>)responseService.getListResult((List<T>)result);
	}
	
	public CommonResult GetPostDetail(@PathVariable("id")Long id){
		Optional<Board> post = BoardRepository.findById(id);
		if (post.isPresent()) {
			return responseService.getSingleResult(post);
		}
		return responseService.getFailResult();
		
	}
	
	public SingleResult<Board> CreatePost(@RequestBody Board newpost) {
		Board post = new Board();
		Optional<User> user = UserRepository.findById(newpost.getUser().getUid());
		post.setUser(user.get());
		post.setTitle(newpost.getTitle());
		post.setContent(newpost.getContent());
		BoardRepository.save(post);
		return responseService.getSingleResult(post);
	}
	
	public SingleResult<Board> EditPost(@RequestBody JSONObject editpost, @PathVariable("id")Long id) {
		Board post = BoardRepository.findById(id).get();
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
	
	public CommonResult DeletePost(@PathVariable("id")Long id) {
		if(BoardRepository.findById(id).isEmpty()) {
			return responseService.getFailResult();
		}
		BoardRepository.deleteById(id);
		return responseService.getSingleResult("success");
	}
	
}
