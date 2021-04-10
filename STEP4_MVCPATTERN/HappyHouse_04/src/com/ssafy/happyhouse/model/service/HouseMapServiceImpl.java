package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dao.HouseMapDaoImpl;

public class HouseMapServiceImpl implements HouseMapService {
	
	private static HouseMapService houseMapService;
	
	private HouseMapServiceImpl() {}
	
	public static HouseMapService getHouseMapService() {
		if(houseMapService == null)
			houseMapService = new HouseMapServiceImpl();
		return houseMapService;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return HouseMapDaoImpl.getHouseMapDao().getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return HouseMapDaoImpl.getHouseMapDao().getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return HouseMapDaoImpl.getHouseMapDao().getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return HouseMapDaoImpl.getHouseMapDao().getAptInDong(dong);
	}

}
