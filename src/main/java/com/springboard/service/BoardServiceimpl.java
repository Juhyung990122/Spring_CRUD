package com.springboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboard.domain.Board;
import com.springboard.persistence.BoardRepository;

@Service("BoardService")
public class BoardServiceimpl {
	
	@Autowired
	private BoardRepository BoardRepository;

	public Iterable<Board> GetPost() {
		return BoardRepository.findAll();
	}
}
