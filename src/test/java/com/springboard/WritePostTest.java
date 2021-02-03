package com.springboard;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.java.Log;

import com.springboard.persistence.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WritePostTest {
		
		@Autowired
		BoardRepository BoardRepository;
		
		@Autowired
		UserRepository userRepository;
}
