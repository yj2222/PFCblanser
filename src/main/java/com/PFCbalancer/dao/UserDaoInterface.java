package com.PFCbalancer.dao;

import org.springframework.dao.DataAccessException;

import com.PFCbalancer.model.User;

public interface UserDaoInterface {
	
	public User selectOne(String nickname, String password) throws DataAccessException;

}
