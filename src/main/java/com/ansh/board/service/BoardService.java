package com.ansh.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ansh.board.model.BoardDTO;
import com.ansh.board.model.BoardVO;

public interface BoardService {
	Page<BoardVO> getBoardlist(Pageable pageable, Map<String, Object> requestMap);
	
	Integer[] getPageList(Integer curPageNum);
	
	BoardDTO getPost(Long id);
	
	//Long savePost(BoardDTO boardDTO);
	
	void deletePost(Long id);
	
	//List<BoardDTO> searchPosts(String keyword);
}
