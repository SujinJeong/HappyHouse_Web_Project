package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;

public interface HouseDealDao {

	// 동별 검색
		public List<HouseDealDto> searchByDong(String dong) throws Exception;
		// 아파트별 검색
		public List<HouseDealDto> searchByAptName(String AptName) throws Exception;
		// 모든 거래 정보 출력
		public List<HouseDealDto> listAll() throws Exception;
		//코드와 아파트명으로 실시간 거래정보 가져오기
		public List<HouseDealDto> joinByCode(String dong, String AptName) throws Exception;
}
