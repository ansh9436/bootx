package com.ansh.board.controller;

import com.ansh.board.model.BoardDTO;
import com.ansh.board.service.BoardService;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;


    @GetMapping("/")
    public String list(Model model, @RequestParam(value="page", defaultValue="1") Integer pageNum) {
        //List<BoardDTO> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

       // model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        
        return "board/list.html";
    }
    
    @RequestMapping(path="/board", method=RequestMethod.POST)
    public ResponseEntity<?> list(@PageableDefault Pageable pageable,
    					@RequestParam(value="searchType", defaultValue="1") String searchType,
    					@RequestParam(value="searchKeyword") String searchKeyword,
    					@RequestParam(value="pageNum", defaultValue="1") Integer pageNum) {
        
    	ResponseEntity<Pageable> entity = null;
    	
    	Map<String, Object> requestMap = new HashMap<String, Object>();
    	requestMap.put("searchType", 	searchType);
    	requestMap.put("searchKeyword", searchKeyword);
    	requestMap.put("pageNum", 		pageNum);
    	
    	
    	
    	try {
    		entity = new ResponseEntity<Pageable>(pageable, HttpStatus.OK);
    	} catch (Exception e) {
	        e.printStackTrace();
	        entity = new ResponseEntity<Pageable>(HttpStatus.OK);
	    }
    	return entity;
    }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDTO boardDTO) {
        //boardService.savePost(boardDTO);

        return "redirect:/";
    }
    
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDTO boardDTO = boardService.getPost(no);

        model.addAttribute("boardDTO", boardDTO);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDTO boardDTO = boardService.getPost(no);

        model.addAttribute("boardDTO", boardDTO);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDTO boardDTO) {
    	System.out.println("put_______________");
        //boardService.savePost(boardDTO);

        return "redirect:/";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }
    
    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        //List<BoardDTO> boardDTOList = boardService.searchPosts(keyword);

        //model.addAttribute("boardList", boardDTOList);

        return "board/list.html";
    }
}