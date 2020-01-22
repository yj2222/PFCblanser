package com.PFCbalancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PFCbalancer.dao.UserDaoInterface;
import com.PFCbalancer.model.User;

@Transactional
@Service
public class UserService {
	
	@Autowired
    private UserDaoInterface userDao;
	
	public boolean insertOne(User user){
		boolean result = false;
		
		int rowNumber = userDao.insertOne(user);
		if(rowNumber != 0) result = true;
		
		System.out.println("行番号＝" + rowNumber);
		System.out.println("真偽値＝" + result);
		
		return result;
	}

}
