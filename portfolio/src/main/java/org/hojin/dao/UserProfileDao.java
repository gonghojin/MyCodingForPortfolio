package org.hojin.dao;

import java.util.List;

import org.hojin.model.UserProfile;

public interface UserProfileDao {
	
	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
	
}
