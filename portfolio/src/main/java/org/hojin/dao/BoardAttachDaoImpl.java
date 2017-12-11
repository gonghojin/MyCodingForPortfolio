package org.hojin.dao;

import java.math.BigInteger;

import org.hojin.model.BoardAttach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardAttachDaoImpl extends AbstractDao<Integer, BoardAttach> implements BoardAttachDao{
	
	static final Logger logger = LoggerFactory.getLogger(BoardAttachDaoImpl.class);
	
	@Override
	public void save(BoardAttach boardAttach) {
		int bno = ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).intValue();
		boardAttach.setBno(bno);
		persist(boardAttach);
	}

}
