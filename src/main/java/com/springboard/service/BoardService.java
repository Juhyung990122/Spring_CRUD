package com.springboard.service;

import java.util.Optional;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.domain.Board;

import net.minidev.json.JSONObject;

public interface BoardService {
	public Iterable<Board> GetPost();
	public Optional<Board> GetPostDetail(@PathVariable("id")Long id);
	public Board CreatePost(@RequestBody Board newpost);
	public Board EditPost(@RequestBody JSONObject editpost, @PathVariable("id")Long id);
}
