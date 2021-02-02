package com.springboard.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.springboard.domain.*;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
	
}
