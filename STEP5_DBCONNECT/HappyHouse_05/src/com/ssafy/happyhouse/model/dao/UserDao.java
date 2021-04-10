package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.UserDto;

public interface UserDao {
	// 로그인
	UserDto login(String userId, String userPwd);

	// 비밀번호 변경
	void changePwd(String userId, String userPwd);

	// 회원정보 수정
	void modifyMember(String userId);

	// 회원정보 조회
	List<UserDto> getUser(String userId);

	// 회원가입
	public void registerUser(String userId, String userPwd, String userName, String userAddr, String userTel);

	// 회원탈퇴
	void deleteMember(String userId);
}
