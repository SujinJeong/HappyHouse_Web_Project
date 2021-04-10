package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.FavoriteDto;

public interface FavoriteService {
	public void insertFav(FavoriteDto favVO) throws Exception;
	public void updateFav(FavoriteDto favVO) throws Exception;
	public void deleteFav(int userId, long areaCode) throws Exception;
	public FavoriteDto selectFav(int userId, long areaCode) throws Exception;
	public List<FavoriteDto> selectByUserId(int userid) throws Exception;
	public List<FavoriteDto> selectByAreaCode(long areaCode) throws Exception;
}
