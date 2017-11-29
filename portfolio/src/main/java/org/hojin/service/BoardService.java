package org.hojin.service;

import java.util.List;

import org.hojin.model.Board;
import org.hojin.model.RequestPage;
import org.hojin.model.SearchPage;

public interface BoardService {
	Board findById(int id);
	
	void save(Board board);
	
	void deleteById(int id);
	
	List<Board> findAllBoard(RequestPage reqPage);
	
	Board updateBoard(Board board);
	
	Integer count();
	
	List<Board> listSearch(SearchPage searchPage);
	
	Integer countListSearch(SearchPage searchPage);
}
