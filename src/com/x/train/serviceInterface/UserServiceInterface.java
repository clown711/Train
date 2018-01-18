package com.x.train.serviceInterface;

import java.util.ArrayList;
import java.util.Map;

import com.x.train.bean.User;

public interface UserServiceInterface {
	
	public int insert(User user) throws Exception;
	
	public User selectUserByUsername(User user) throws Exception;
	
	public ArrayList<User> selectUser(Map<String, Object> pageMap) throws Exception;
	
	public int getUserCountNumber() throws Exception;
	
	public int deleteUserById(int userId) throws Exception;
}
