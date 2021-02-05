package com.springboard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboard.domain.Board;
import com.springboard.persistence.BoardRepository;

@Service("BoardService")
public class BoardServiceimpl implements BoardService {
	
	@Autowired
	private BoardRepository BoardRepository;

	public Iterable<Board> GetPost() {
		return BoardRepository.findAll();
	}
	
	public Optional<Board> GetPostDetail(@PathVariable("id")Long id){
		return BoardRepository.findById(id);
	}
	
}
