package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String act = request.getParameter("act");
		response.setCharacterEncoding("utf-8");
		if ("mvlogin".equals(act)) {
			response.sendRedirect(root + "/login.jsp");
		} else if ("mvsignUp".equals(act)) {
			response.sendRedirect(root + "/signUp.jsp");
		} else if ("login".equals(act)) {
			login(request, response);
		} else if ("logout".equals(act)) {
			logout(request, response);
		} else if ("registerUser".equals(act)) {
			registerUser(request, response);
		} else if ("getUser".equals(act)) {
			getUser(request, response);
		} else if ("deleteUser".equals(act)) {
			deleteUser(request, response);
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		
		UserDto userDto = UserServiceImpl.getUserService().login(userId, userPwd);

		String path = "/index.jsp";
		if(userDto != null) { // 성공
//			session 설정
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", userDto);
			
//			Cookie 설정
			String idsv = request.getParameter("idsave");
			if("saveok".equals(idsv)) { // 아이디 저장 O
				Cookie cookie = new Cookie("save_id", userId);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				
				response.addCookie(cookie);
			} else { // 아이디 저장 X
				Cookie cookies[] = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("save_id")) {
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							break;
						}
					}
				}
			}
			
		} else { // 실패
			request.setAttribute("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
			
			path = "/login.jsp";
		}
		
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	private void changePwd(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		String userName = request.getParameter("username");
		String userAddr = request.getParameter("useraddr");
		String userTel = request.getParameter("usertel");

		try {
			UserServiceImpl.getUserService().registerUser(userId, userPwd, userName, userAddr, userTel);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 실패");
			path = "/error/error.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}
	
	private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String userId = request.getParameter("userid");
		try {
			List<UserDto> list = userService.getUser(userId);
			request.setAttribute("user", list);
			path = "";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "검색하신 단어에 해당하는 회원 없습니다.");
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {

	}

}
