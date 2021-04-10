package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private static UserService userService;
	
	private UserServiceImpl() {}

	public static UserService getUserService() {
		if(userService == null)
			userService = new UserServiceImpl();
		return userService;
	}

	@Override
	public UserDto login(String userId, String userPwd) {
		if(userId == null || userPwd == null)
			return null;
		return UserDaoImpl.getUserDao().login(userId, userPwd);
	}

	@Override
	public void changePwd(String userId, String userPwd) {
		// TODO Auto-generated method stub
		
		UserDaoImpl.getUserDao().changePwd(userId, userPwd);
	}

	@Override
	public void registerUser(String userId, String userPwd, String userName, String userAddr, String userTel) {
		UserDaoImpl.getUserDao().registerUser(userId, userPwd, userName, userAddr, userTel);
	}
	
	@Override
	public List<UserDto> getUser(String userId) {
		return UserDaoImpl.getUserDao().getUser(userId);
	}
	
	@Override
	public void modifyMember(String userId) {
		UserDaoImpl.getUserDao().modifyMember(userId);
	}

	@Override
	public void deleteMember(String userId) {
		UserDaoImpl.getUserDao().deleteMember(userId);
	}

}
