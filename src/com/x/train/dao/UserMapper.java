package com.x.train.dao;

import com.x.train.bean.User;

import java.util.ArrayList;
import java.util.Map;


public interface UserMapper {
	
	public int insert(User user);
	
	public User selectUserByUsername(String userName);
	
	public ArrayList<User> selectUser(Map<String, Object> pageMap);
	
	public int getUserCountNumber();
	
	public int deleteUserById(int userId);
	
}