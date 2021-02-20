package com.ansh.board.service;

import java.util.List;

import com.ansh.board.model.BoardDTO;

public interface BoardService {
	List<BoardDTO> getBoardlist(Integer pageNum);
	
	Integer[] getPageList(Integer curPageNum);
	
	BoardDTO getPost(Long id);
	
	Long savePost(BoardDTO boardDTO);
	
	void deletePost(Long id);
	
	List<BoardDTO> searchPosts(String keyword);
	
	//BoardDTO convertEntityToDto(BoardVO vo);
}
