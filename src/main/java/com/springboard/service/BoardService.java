package com.springboard.service;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.domain.Board;

public interface BoardService {
	public Iterable<Board> GetPost();
	public Optional<Board> GetPostDetail(@PathVariable("id")Long id);
	public Board CreatePost(@RequestBody Board newpost);
}

