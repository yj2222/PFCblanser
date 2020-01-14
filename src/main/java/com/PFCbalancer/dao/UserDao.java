package com.PFCbalancer.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.PFCbalancer.model.User;

@Repository("UserDao")
public class UserDao implements UserDaoInterface {
	
	@Autowired
    JdbcTemplate jdbc;

	
	@Override
    public User selectOne(String nickname, String password) throws DataAccessException {
		
		User user = new User();

		try {
			Map<String, Object> map = jdbc.queryForMap("SELECT * FROM account"
	                + " WHERE nickname = ? and password = ?", nickname, password);
	        
	        System.out.println("マップの中身＝" + map);

	        user.setNickname((String) map.get("nickname"));
	        user.setPassword((String) map.get("password")); 
	        user.setEmail((String) map.get("email"));

	        return user;
		} catch(EmptyResultDataAccessException e) {
			return user;
		}
         
    }

}