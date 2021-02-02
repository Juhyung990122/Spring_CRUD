package com.springboard;


import java.util.Optional;

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
	
	@Test
	public void InsertTest() {
		Board board = new Board();
		board.setTitle("test title");
		board.setContent("test content");
		board.setWriter("guinness");
		
		BoardRepository.save(board);
	}
	
	@Test
	public void testRead() {
		BoardRepository.findById(1L).ifPresent((board)->{System.out.println(board);});
	}
	
	@Test
	public void testUpdate() {
		Board board = BoardRepository.findById(1L).orElse(null);
		board.setTitle("new title");
		System.out.println("update");
		BoardRepository.save(board);
		
	}
	
	@Test
	public void testDelete() {
		System.out.println("delete");
		BoardRepository.deleteById(1L);
	}
}
