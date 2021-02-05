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
		System.out.println(newpost.getUser().getUid());
		Board post = new Board();
		Optional<User> user = UserRepository.findById(newpost.getUser().getUid());
		post.setUser(user.get());
		post.setTitle(newpost.getTitle());
		post.setContent(newpost.getContent());
		BoardRepository.save(post);
		return post;
	}
	
}
