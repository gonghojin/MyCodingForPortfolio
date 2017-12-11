package org.hojin.dao;

import java.util.List;

import org.hojin.model.Board;
import org.hojin.model.BoardAttach;
import org.hojin.model.BoardFile;
import org.hojin.model.RequestPage;
import org.hojin.model.SearchPage;

public interface BoardDao {
	
	Board findById(int id);
	
	void save(Board board);
	
	void deleteById(int id);
	
	
	Board updateBoard(Board board);

	List<Board> findAllBoard(RequestPage reqPage);
	
	Integer count();
	
	List<Board> listSearch(SearchPage searchPage);
	
	
	Integer countListSearch(SearchPage searchPage);
	
	
}
