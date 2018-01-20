package com.tryall.mapper;

import org.apache.ibatis.annotations.Param;

import com.tryall.pojo.User;

public interface UserMapper {

	public User getUser(@Param("id") int id);
	
	public void addUser(@Param("username")String username,
			@Param("password")String password);
	
	public User findUserByUsername(@Param("username")String username); 
}

