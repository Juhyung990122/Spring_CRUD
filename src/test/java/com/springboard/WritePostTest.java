package com.springboard;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.java.Log;

import com.springboard.domain.Board;
import com.springboard.domain.User;
import com.springboard.persistence.*;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WritePostTest {
		
		@Autowired
		BoardRepository BoardRepository;
		
		@Autowired
		UserRepository userRepository;
		/*
		@Test
		public void InsertTestUser() {
			IntStream.range(1,11 ).forEach(i->{
				User user = new User();
				user.setUid(i);
				user.setEmail("test"+i+"@naver.com");
				user.setPassword("1212");
				user.setUser_name("사용자"+i);
				
				userRepository.save(user);
			});
		}
		
		@Test
		public void InsertTestPost() {
			User user = new User();
			user.setUid(1);
			
			for(int i=0; i<5; i++) {
				Board board = new Board();
				board.setTitle(i+"title");
				board.setContent(i + "post");
				board.setUser(user);
				BoardRepository.save(board);
			}
			
		}*/
		
}
