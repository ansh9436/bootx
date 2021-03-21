package com.ansh.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ansh.board.model.BoardVO;

@Repository
public interface BoardRepository extends JpaRepository<BoardVO, Long> {
	Page<BoardVO> findAllByTitleContaining(String searchKeyword, Pageable pageable);
	Page<BoardVO> findAllByContentContaining(String searchKeyword, Pageable pageable);
	Page<BoardVO> findAllByWriterContaining(String searchKeyword, Pageable pageable);
}