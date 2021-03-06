package com.ansh.board.service;

import com.ansh.board.model.BoardDTO;
import com.ansh.board.model.BoardVO;
import com.ansh.board.repository.BoardRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
	private BoardRepository boardRepository;
    
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 5;       // 한 페이지에 존재하는 게시글 수

    public Page<BoardVO> getBoardlist(Pageable pageable, Map<String, Object> requestMap) {
    	Page<BoardVO> boardList = null;
    	int pageNum 			= Integer.parseInt(requestMap.get("pageNum").toString());
    	String searchType 		= requestMap.get("searchType").toString();
    	String searchKeyword 	= requestMap.get("searchKeyword").toString();

    	pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 
    			0 : pageNum - 1, PAGE_POST_COUNT, Sort.Direction.DESC, "id");
    	
    	if(searchKeyword.length() > 2) {
    		switch (searchType) {
	 	       case "1":
	 	    	  boardList = boardRepository.findAllByTitleContaining(searchKeyword,pageable);
	 	           break;
	 	       case "2":
	 	    	  boardList = boardRepository.findAllByContentContaining(searchKeyword,pageable);
	 	           break;
	 	       case "3":
	 	    	  boardList = boardRepository.findAllByWriterContaining(searchKeyword,pageable);
	 	           break;
	 	   }
    	} else {
    		boardList = boardRepository.findAll(pageable);
    	}
    	return boardList;
    }
    
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(boardRepository.count());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }
        return pageList;
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
    
//    @Transactional
//    public Long savePost(BoardDTO boardDTO) {
//        return boardRepository.save(boardDTO.toEntity()).getId();
//    }
    
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
    
//    public List<BoardDTO> searchPosts(String keyword) {
//        List<BoardVO> boardVOs = boardRepository.findAllByTitleContaining(keyword);
//        List<BoardDTO> boardDTOList = new ArrayList<>();
//
//        if (boardVOs.isEmpty()) return boardDTOList;
//
//        for (BoardVO vo : boardVOs) {
//            boardDTOList.add(this.convertEntityToDto(vo));
//        }
//
//        return boardDTOList;
//    }
    
//    private BoardDTO convertEntityToDto(BoardVO vo) {
//        return BoardDTO.builder()
//                .id(vo.getId())
//                .title(vo.getTitle())
//                .content(vo.getContent())
//                .writer(vo.getWriter())
//                .createdDate(vo.getCreatedDate())
//                .build();
//    }
}