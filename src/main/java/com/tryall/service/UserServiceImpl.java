package com.tryall.service;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryall.mapper.UserMapper;
import com.tryall.pojo.User;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper usermapper;
	
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return usermapper.getUser(id);
	}

	
	
	
	@Override
	public void addUser(String username, String password) {
		// TODO Auto-generated method stub
		SimpleHash simpleHash = new SimpleHash("MD5", password);
		 usermapper.addUser(username, simpleHash.toString());
		 
	}




	@Override
	public User findUserByUsername(String username) {		
		return usermapper.findUserByUsername(username);
	}

}
