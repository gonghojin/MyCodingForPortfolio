package org.hojin.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hojin.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public User findById(int id) {
		User user = getByKey(id);
		
		if(user != null){
			//Many to Many에서 lazy는 주 테이블이 호출될 때 자동적으로 부 테이블이 호출되지 않음,따라서 관련 테이블을 수동적으로 호출
			Hibernate.initialize(user.getUserProfiles());
		}
		
		return user;
	}

	@Override
	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;

		
	}

	@Override
	public void save(User user) {
		persist(user);
		
	}

	@Override
	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		delete(user);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<User> users = (List<User>) criteria.list();
        /*UserProfile은 페이지에 안 올리니깐 안 하는 거 필요하면 밑에하면됨
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/

		return users;
	}

}
