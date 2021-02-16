package com.ansh.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansh.board.model.BoardVO;


public interface BoardRepository extends JpaRepository<BoardVO, Long> {
	List<BoardVO> findByTitleContaining(String keyword);
}