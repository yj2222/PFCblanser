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
	        
	        user.setNickname((String) map.get("nickname"));
	        user.setPassword((String) map.get("password")); 
	        user.setEmail((String) map.get("email"));

	        return user;
		} catch(EmptyResultDataAccessException e) {
			return user;
		}
    }
	
    @Override
    public int count(String nickname) throws DataAccessException {

        // DBから該当レコードをcountする。
    	String sql = "SELECT COUNT(*) FROM account WHERE nickname=" + "'" + nickname + "'";
        int count = jdbc.queryForObject(sql, Integer.class);

        return count;
    }
	
	@Override
	public int insertOne(User user) throws DataAccessException{
		
		// Userが登録されているかを確認する。
		int count = count(user.getNickname());
		System.out.println("レコード");
		
		int rowNumber = 0;
		if(count == 0) {
			/*
			 * 戻り値は更新行数。0なら更新されなかった事になる。
			 * http://itdoc.hitachi.co.jp/manuals/3000/3000650220/HAPG0156.HTM
			 */
			rowNumber = jdbc.update("INSERT IGNORE INTO account(email,nickname,password) VALUES(?, ?, ?)", 
					user.getEmail(), user.getNickname(), user.getPassword());
		}
		
		return rowNumber;
	}

}