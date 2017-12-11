
package org.hojin.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hojin.model.Board;
import org.hojin.model.BoardAttach;
import org.hojin.model.BoardFile;
import org.hojin.model.RequestPage;
import org.hojin.model.SearchPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDaoImpl extends AbstractDao<Integer, Board> implements BoardDao{
	
	static final Logger logger = LoggerFactory.getLogger(BoardDaoImpl.class);
	@Override
	public Board findById(int id) {
		Board board = getByKey(id);
		return board;
	}

	@Override
	public void save(Board board) {
		int id = persist(board);
		logger.info("board id.................. " + id);
	}

	@Override
	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("bno", id));
		Board board = (Board) crit.uniqueResult();
		delete(board);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Board> findAllBoard(RequestPage reqPage) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("bno"));
		criteria.setFirstResult(reqPage.getPageStart());
		criteria.setMaxResults(6);
		
		List<Board> boards = (List<Board>) criteria.list();
		
		return boards;
	}

	@Override
	public Board updateBoard(Board board) {
		return null;
	}

	@Override
	public Integer count() {
		
		return ((Number)createEntityCriteria().
				setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Board> listSearch(SearchPage searchPage) {
		Criteria criteria = createEntityCriteria();
		String keyword = searchPage.getKeyword();
		
		if(searchPage.getSearchType().equals("t")){
			criteria.add(Restrictions.like("title", "%" + keyword + "%"));
		}else if(searchPage.getSearchType().equals("w")){
			criteria.add(Restrictions.like("writer", "%" + keyword + "%"));
		}
			criteria.addOrder(Order.desc("bno"));
			criteria.setFirstResult(searchPage.getPageStart());
			criteria.setMaxResults(6);
			
		
		List<Board> boards = (List<Board>) criteria.list();
		return boards;
	}

	@Override
	public Integer countListSearch(SearchPage searchPage) {
		Criteria criteria = createEntityCriteria();
		String keyword = searchPage.getKeyword();
		
		if(searchPage.getSearchType().equals("t")){
			criteria.add(Restrictions.like("title", "%" + keyword + "%"));
		}else if(searchPage.getSearchType().equals("w")){
			criteria.add(Restrictions.like("writer", "%" + keyword + "%"));
		}
		
		return ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}



}
