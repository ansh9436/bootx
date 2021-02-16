package com.ansh.board.service;

import com.ansh.board.model.BoardDTO;
import com.ansh.board.model.BoardVO;
import com.ansh.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDTO> getBoardlist() {
        List<BoardVO> boardVOList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for(BoardVO boardVO : boardVOList) {
            BoardDTO boardDTO = BoardDTO.builder()
                    .id(boardVO.getId())
                    .title(boardVO.getTitle())
                    .content(boardVO.getContent())
                    .writer(boardVO.getWriter())
                    .createdDate(boardVO.getCreatedDate())
                    .build();

            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }
    
    @Transactional
    public BoardDTO getPost(Long id) {
        Optional<BoardVO> boardEntityWrapper = boardRepository.findById(id);
        BoardVO boardVO = boardEntityWrapper.get();

        BoardDTO boardDTO = BoardDTO.builder()
                .id(boardVO.getId())
                .title(boardVO.getTitle())
                .content(boardVO.getContent())
                .writer(boardVO.getWriter())
                .createdDate(boardVO.getCreatedDate())
                .build();

        return boardDTO;
    }
    
    @Transactional
    public Long savePost(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO.toEntity()).getId();
    }
    
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
    
    @Transactional
    public List<BoardDTO> searchPosts(String keyword) {
        List<BoardVO> boardVOs = boardRepository.findByTitleContaining(keyword);
        List<BoardDTO> boardDTOList = new ArrayList<>();

        if (boardVOs.isEmpty()) return boardDTOList;

        for (BoardVO vo : boardVOs) {
            boardDTOList.add(this.convertEntityToDto(vo));
        }

        return boardDTOList;
    }
    
    private BoardDTO convertEntityToDto(BoardVO vo) {
        return BoardDTO.builder()
                .id(vo.getId())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .createdDate(vo.getCreatedDate())
                .build();
    }
}