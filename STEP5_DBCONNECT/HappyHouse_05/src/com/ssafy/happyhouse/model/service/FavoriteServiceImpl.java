package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.dao.FavoriteDaoImpl;

public class FavoriteServiceImpl implements FavoriteService {

	private static FavoriteService favoriteService;

	private FavoriteServiceImpl() {
	}

	public static FavoriteService getFavoriteService() {
		if (favoriteService == null)
			favoriteService = new FavoriteServiceImpl();
		return favoriteService;
	}
	@Override
	public void insertFav(FavoriteDto favVO) throws Exception {
		FavoriteDaoImpl.getFavoriteDao().insertFav(favVO);
	}

	@Override
	public void updateFav(FavoriteDto favVO) throws Exception {
		FavoriteDaoImpl.getFavoriteDao().updateFav(favVO);
		
	}

	@Override
	public void deleteFav(int userId, long areaCode) throws Exception {
		FavoriteDaoImpl.getFavoriteDao().deleteFav(userId, areaCode);
	}

	@Override
	public FavoriteDto selectFav(int userId, long areaCode) throws Exception {
		return FavoriteDaoImpl.getFavoriteDao().selectFav(userId, areaCode);
	}

	@Override
	public List<FavoriteDto> selectByUserId(int userid) throws Exception {
		return FavoriteDaoImpl.getFavoriteDao().selectByUserId(userid);
	}

	@Override
	public List<FavoriteDto> selectByAreaCode(long areaCode) throws Exception {
		return FavoriteDaoImpl.getFavoriteDao().selectByAreaCode(areaCode);
	}

}
