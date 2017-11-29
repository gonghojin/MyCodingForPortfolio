package org.hojin.service;

import java.util.List;

import org.hojin.dao.BoardDao;
import org.hojin.model.Board;
import org.hojin.model.RequestPage;
import org.hojin.model.SearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;
	
	@Override
	public Board findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void save(Board board) {
		dao.save(board);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Board> findAllBoard(RequestPage reqPage) {
		return dao.findAllBoard(reqPage);
	}

	@Override
	public Board updateBoard(Board board) {
		Board entity = dao.findById(board.getBno());
		
		if(entity != null){
			entity.setTitle(board.getTitle());
			entity.setContent(board.getContent());
			
			
		}
		
		return entity;
			
		
	}

	@Override
	public Integer count() {
		return dao.count();
	}

	@Override
	public List<Board> listSearch(SearchPage searchPage) {
		if(searchPage.getSearchType() == null || searchPage.getSearchType().trim().equals("")){
			if(searchPage.getKeyword() == null || searchPage.getKeyword().trim().equals("")){
				return dao.findAllBoard(searchPage);
			}
		}
		return dao.listSearch(searchPage);
	}

	@Override
	public Integer countListSearch(SearchPage searchPage) {
		if(searchPage.getSearchType() == null || searchPage.getSearchType().trim().equals("")){
			if(searchPage.getKeyword() == null || searchPage.getKeyword().trim().equals("")){
				return dao.count();
			}
		}
		return dao.countListSearch(searchPage);
	}

}
