package com.tryall.service;

import com.tryall.pojo.User;

public interface UserService {

	public User getUser(int id);
	
	public void addUser(String username,String password);
	
	public User findUserByUsername(String username); 
	
	public User solrTest(int userId);
}
