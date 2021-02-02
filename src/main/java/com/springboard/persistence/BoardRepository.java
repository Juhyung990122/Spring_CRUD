package com.springboard.persistence;

import java.util.List;
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.springboard.domain.*;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
	public List<Board> findBoardByTitle(String title);
	public Collection<Board> findByWriter(String writer);
}
