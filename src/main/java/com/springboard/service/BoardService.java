package com.springboard.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.domain.Board;
import com.springboard.response.CommonResult;
import com.springboard.response.ListResult;
import com.springboard.response.SingleResult;

import net.minidev.json.JSONObject;

public interface BoardService {
	public ListResult<Iterable<Board>> GetPost();
	public CommonResult GetPostDetail(@PathVariable("id")Long id);
	public SingleResult<Board> CreatePost(@RequestBody Board newpost,Authentication authentication);
	public CommonResult EditPost(@RequestBody JSONObject editpost, @PathVariable("id")Long id,Authentication authentication);
	public CommonResult DeletePost(@PathVariable("id")Long id,Authentication authentication);
}
