package com.ssafy.happyhouse.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 관심지역 설정 구현
@WebServlet("/favorite")
public class FavoriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String act = request.getParameter("act");
		
		response.setCharacterEncoding("utf-8");
		if ("insertFav".equals(act)) {
			insertFav(request, response);
		} else if ("updateFav".equals(act)) {
			updateFav(request, response);
		} else if ("deleteFav".equals(act)) {
			deleteFav(request, response);
		} else if("selectFav".equals(act)){
			selectFav(request, response);
		} else if("selectByUserId".equals(act)){
			selectByUserId(request, response);
		} else if("selectByAreaCode".equals(act)){
			selectByAreaCode(request, response);
		} else {
			response.sendRedirect(root);
		}
	}
	
	// 관심지역 insert
	private void insertFav(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	// 해당 인덱스 관심지역 정보 변경 
	private void updateFav(HttpServletRequest request, HttpServletResponse response) {
			
	}
	
	// 관심지역 정보 delete
	private void deleteFav(HttpServletRequest request, HttpServletResponse response) {
				
	}
	
	// 해당 관심지역 정보 1개 가져오기
	private void selectFav(HttpServletRequest request, HttpServletResponse response) {
					
	}
	
	// 해당 유저에 대한 관심지역 정보 모두 가져오기
	private void selectByUserId(HttpServletRequest request, HttpServletResponse response) {
						
	}
		
	// 해당 지역에 대한 관심지역 정보 모두 가져오기
	private void selectByAreaCode(HttpServletRequest request, HttpServletResponse response) {
						
	}
}
