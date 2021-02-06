package com.springboard.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.domain.Board;
import com.springboard.domain.User;
import com.springboard.persistence.BoardRepository;
import com.springboard.persistence.UserRepository;

import net.minidev.json.JSONObject;

@Service("BoardService")
public class BoardServiceimpl implements BoardService {
	
	@Autowired
	private BoardRepository BoardRepository;
	@Autowired
	private UserRepository UserRepository;

	public Iterable<Board> GetPost() {
		return BoardRepository.findAll();
	}
	
	public Optional<Board> GetPostDetail(@PathVariable("id")Long id){
		return BoardRepository.findById(id);
	}
	
	public Board CreatePost(@RequestBody Board newpost) {
		Board post = new Board();
		Optional<User> user = UserRepository.findById(newpost.getUser().getUid());
		post.setUser(user.get());
		post.setTitle(newpost.getTitle());
		post.setContent(newpost.getContent());
		BoardRepository.save(post);
		return post;
	}
	
	public Board EditPost(@RequestBody JSONObject editpost, @PathVariable("id")Long id) {
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
		return post;
	}
	
}
