package com.springboard.service;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.springboard.domain.Board;

public interface BoardService {
	public Iterable<Board> GetPost();
	public Optional<Board> GetPostDetail(@PathVariable("id")Long id);
}

