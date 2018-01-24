package com.tryall.service;

import java.util.List;

import com.tryall.pojo.User;

public interface UserService {

	public User getUser(int id);
	
	public void addUser(String username,String password);
	
	public User findUserByUsername(String username); 
	
	public User solrTest(int userId);
}
