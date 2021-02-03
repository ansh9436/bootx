package com.ansh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ansh.board.model.BoardVO;

@RepositoryRestResource(collectionResourceRel = "board", path = "board")
public interface BoardRepository extends JpaRepository<BoardVO, Long> {
}