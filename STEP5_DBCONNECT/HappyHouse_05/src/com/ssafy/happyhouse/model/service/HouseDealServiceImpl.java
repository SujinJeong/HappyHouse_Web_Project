package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.dao.HouseDealDao;
import com.ssafy.happyhouse.model.dao.HouseDealDaoImpl;

public class HouseDealServiceImpl implements HouseDealService {

	private static HouseDealService houseDealService;

	private HouseDealServiceImpl() {
	}

	public static HouseDealService getHouseDealService() {
		if (houseDealService == null)
			houseDealService = new HouseDealServiceImpl();
		return houseDealService;
	}

	@Override
	public List<HouseDealDto> searchByDong(String dong) throws Exception {
		return HouseDealDaoImpl.getHouseDealDao().searchByDong(dong);
	}

	@Override
	public List<HouseDealDto> searchByAptName(String AptName) throws Exception {
		return HouseDealDaoImpl.getHouseDealDao().searchByAptName(AptName);
	}

	@Override
	public List<HouseDealDto> listAll() throws Exception {
		return HouseDealDaoImpl.getHouseDealDao().listAll();
	}

	@Override
	public List<HouseDealDto> joinByCode(String dong, String AptName) throws Exception {
		return HouseDealDaoImpl.getHouseDealDao().joinByCode(dong, AptName);
	}
}
