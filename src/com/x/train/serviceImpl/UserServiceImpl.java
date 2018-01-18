package com.x.train.serviceImpl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.x.train.bean.User;
import com.x.train.dao.UserMapper;
import com.x.train.serviceInterface.UserServiceInterface;
import com.x.train.util.EncodeUtil;

@Service
public class UserServiceImpl implements UserServiceInterface {
	
	private UserMapper userMapper;
	private EncodeUtil encodeUtil;
	
	public EncodeUtil getEncodeUtil() {
		return encodeUtil;
	}
	
	@Resource
	public void setEncodeUtil(EncodeUtil encodeUtil) {
		this.encodeUtil = encodeUtil;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}
	
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public int insert(User user) throws Exception {
		// TODO Auto-generated method stub
		User oldUser = userMapper.selectUserByUsername(user.getUserAccount());
		if(oldUser!=null){
			throw new Exception("账号重复");
		}
		else{
			user.setUserPassword(encodeUtil.md5Encode(user.getUserNickname(),user.getUserPassword()));
		}
		return userMapper.insert(user);
	}

	@Override
	public User selectUserByUsername(User user) throws Exception {
		// TODO Auto-generated method stub
		User oldUser = userMapper.selectUserByUsername(user.getUserAccount());
		if(oldUser == null)
			throw new Exception("账号不存在");
		if(!oldUser.getUserAccount().equals(user.getUserAccount()))
			throw new Exception("账号或密码错误");
		user.setUserPassword(encodeUtil.md5Encode(oldUser.getUserNickname(),user.getUserPassword()));
		if(!oldUser.getUserPassword().equals(user.getUserPassword()))
			throw new Exception("账号或密码错误");
		return oldUser;
	}

	@Override
	public ArrayList<User> selectUser(Map<String, Object> pageMap) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectUser(pageMap);
	}

	@Override
	public int getUserCountNumber() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserCountNumber();
	}

	@Override
	public int deleteUserById(int userId) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.deleteUserById(userId);
	}

}
