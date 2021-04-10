package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.service.HouseDealServiceImpl;

// 동별, 아파트별 검색 기능 구현
@WebServlet("/search")
public class HouseDealController extends HttpServlet {
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
		if ("searchByDong".equals(act)) {
			searchByDong(request, response);
		} else if ("searchByAptName".equals(act)) {
			searchByAptName(request, response);
		} else if ("listAll".equals(act)) {
			listAll(request, response);
		} else if("joinByCode".equals(act)){
			joinByCode(request, response);
		} else {
			response.sendRedirect(root);
		}
	}
	// 해당 동에 해당하는 아파트,주택 거래 정보 긁어오기
	private void searchByDong(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		String dong = request.getParameter("searchString");
		try {
			//System.out.println(dong);
			List<HouseDealDto> list = HouseDealServiceImpl.getHouseDealService().searchByDong(dong);
			request.setAttribute("deals", list);
			//response.sendRedirect(request.getContextPath() + "/jsp/searchResult.jsp");
			path = "/jsp/searchResult.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	// 해당 단어가 들어가는 아파트 정보 모두 긁어오기
	private void searchByAptName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		String AptName = request.getParameter("searchString");
		try {
			
			List<HouseDealDto> list = HouseDealServiceImpl.getHouseDealService().searchByAptName(AptName);
			
			request.setAttribute("deals", list);
			//response.sendRedirect(request.getContextPath() + "/jsp/searchResult.jsp");
			path = "/jsp/searchResult.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	// 모든 아파트,주택 거래 정보 긁어오기
	private void listAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		try {
			List<HouseDealDto> list = HouseDealServiceImpl.getHouseDealService().listAll();
			request.setAttribute("deals", list);
			//path = "/searchByAptName.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void joinByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String dong = request.getParameter("dong");
		String AptName = request.getParameter("AptName");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		List<HouseDealDto> list = null;
		JSONArray arr = new JSONArray();
		try {
			list = HouseDealServiceImpl.getHouseDealService().joinByCode(dong, AptName);
			for(HouseDealDto dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("buildYear", dto.getBuildYear());
				obj.put("AptName", dto.getAptName());
				obj.put("dealAmount", dto.getDealAmount());
				obj.put("floor", dto.getFloor());
				obj.put("area", dto.getArea());
				obj.put("jibun", dto.getJibun());
				arr.add(obj);
			}
		} catch (Exception e) {
			arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("message_code", "-1");
			arr.add(obj);
			e.printStackTrace();
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}
	}
}
