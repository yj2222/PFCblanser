package com.PFCbalancer.dao;

import org.springframework.dao.DataAccessException;

import com.PFCbalancer.model.User;

public interface UserDaoInterface {
	
	public User selectOne(String nickname, String password) throws DataAccessException;
	public int insertOne(User user) throws DataAccessException;
	int count(String name) throws DataAccessException;

}
