package org.hojin.controller;

import static org.hamcrest.CoreMatchers.not;

import javax.validation.Valid;

import org.apache.ibatis.javassist.NotFoundException;
import org.hojin.model.Board;
import org.hojin.model.SearchPage;
import org.hojin.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	MessageSource messageSource;
	
	
	//등록
	@RequestMapping(value = "/newboard", method = RequestMethod.GET)
	public String newboardGET(ModelMap model, @ModelAttribute("reqPage") SearchPage searchType){
		Board board = new Board();
		model.addAttribute("board", board);
		
		return "/board/boardRegister";
	}
	
	@RequestMapping(value = "/newboard", method = RequestMethod.POST)
	public String newboardPOST(@Valid Board board, BindingResult result, ModelMap model, @ModelAttribute("req") SearchPage searchPage){
		if(result.hasErrors()){
			return "/board/boardRegister";
		}
		
		boardService.save(board);
		
		return "redirect:/main";
		
	}
	
	@RequestMapping(value = "/{bno}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Board> boardModal(@PathVariable("bno") Integer bno){
		logger.info("boardModal Working...........");
		ResponseEntity<Board> entity = null;
		
		try{
			
			return entity = new ResponseEntity<Board>(boardService.findById(bno), HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			return entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/{bno}", method = RequestMethod.PUT, produces = "application/text; charset=utf8")
	public ResponseEntity<Object> updateBoard(@PathVariable("bno") Integer bno, @RequestBody Board board, BindingResult result) throws NotFoundException{
		
		logger.info("updateBoard Working......bno : " + bno);
		board.setBno(bno);
		
		//checking board dto whether null or not null
		if(!checkBoard(board)){
			logger.info("updateBoard Value is Null....");
			throw new NotFoundException("제목이나 내용을 채워 주십시오");
		}
		Board entity = boardService.updateBoard(board);
		if(entity == null){
			logger.info("updateBoard's entity is Null.....");
			throw new NotFoundException("updateBoard's entity is Null.....");
			
		}//https://stackoverflow.com/questions/45194807/return-error-from-sprin
		
		return new ResponseEntity<Object>("success", HttpStatus.OK);
	}
	
	// 게시글 지우기
	@RequestMapping(value = "/{bno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("bno") Integer bno){
		logger.info("remove.......... bno : " + bno);
		
		//ResponseEntity<String> entity = null;
		boardService.deleteById(bno);
	
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	
	/**
     * This method returns the principal[user-name] of logged-in user.
     */
	private String getPrincipal(){
		String userName = null;
		return userName;
	
	}
	
	private boolean checkBoard(Board board){
		String title = board.getTitle();
		String content = board.getContent();
		
		if(title.trim().equals("") || content.trim().equals("")){
			return false;
		}
		return true;
		
	}
}
