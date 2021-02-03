package com.springboard;


import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboard.domain.Board;
import com.springboard.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	BoardRepository BoardRepository;
	
	// 쿼리 연습 테스트데이터 200개 생성
	/*
	@Test
	public void Insert200() {
		int i;
		for(i=1; i<=200; i++) {
		Board board = new Board();
		board.setTitle(i+"title");
		board.setContent(i+"content="); 
		board.setWriter("guinness");
		
		BoardRepository.save(board);
		}
	
	*/	
	@Test
	public void TestByTitle() {
		BoardRepository.findBoardByTitle("177title")
		.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void TestBywriter() {
		Collection<Board> result = BoardRepository.findByWriter("");
		
		result.forEach(board->System.out.println(result));
		
	}

	
	@Test
	public void TestByTitleContaining() {
		Collection<Board> result = BoardRepository.findByTitleContaining("7");
		result.forEach(board->System.out.println(result));
	}
}
	
	
