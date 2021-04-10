package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.util.DBUtil;

public class FavoriteDaoImpl implements FavoriteDao {
   private static FavoriteDao favoriteDao;

   private FavoriteDaoImpl() {
   }

   public static FavoriteDao getFavoriteDao() {
      if (favoriteDao == null)
         favoriteDao = new FavoriteDaoImpl();
      return favoriteDao;
   }
   @Override
   public void insertFav(FavoriteDto favVO) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = DBUtil.getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("INSERT INTO Favorite (user_id, user_pwd) VALUES (?, ?)");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, favVO.getUser_id());
         pstmt.setLong(2, favVO.getCode());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBUtil.close(pstmt);
         DBUtil.close(conn);
      }
   }

   @Override
   public void updateFav(FavoriteDto favVO) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DBUtil.getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("UPDATE favorite ");
         sql.append("SET code = ? ");
         sql.append("WHERE user_id = ? ");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setLong(1, favVO.getCode());
         pstmt.setInt(2, favVO.getUser_id());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBUtil.close(pstmt);
         DBUtil.close(conn);
      }
   }

   @Override
   public void deleteFav(int userId, long areaCode) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DBUtil.getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("DELETE FROM favorite WHERE user_id = ? and code = ?");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, userId);
         pstmt.setLong(2, areaCode);
         rs = pstmt.executeQuery();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBUtil.close(rs);
         DBUtil.close(pstmt);
         DBUtil.close(conn);
      }
   }

   @Override
   public FavoriteDto selectFav(int userId, long areaCode) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      FavoriteDto fav = new FavoriteDto();
      try {
         conn = DBUtil.getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("SELECT * FROM User, BaseAddress \n");
         sql.append("WHERE user_id = ? AND code = ? \n");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, userId);
         pstmt.setLong(2, areaCode);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            // dto setting
            fav.setCode(areaCode);
            fav.setUser_id(userId);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBUtil.close(rs);
         DBUtil.close(pstmt);
         DBUtil.close(conn);
      }
      return fav;
   }

   @Override
   public List<FavoriteDto> selectByUserId(int userId) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<FavoriteDto> list = new ArrayList<FavoriteDto>();
      try {
         conn = DBUtil.getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("SELECT * FROM User, BaseAddress \n");
         sql.append("WHERE user_id = ? \n");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, userId);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            FavoriteDto dto = new FavoriteDto();
            // dto setting
            dto.setUser_id(userId);
            list.add(dto);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBUtil.close(rs);
         DBUtil.close(pstmt);
         DBUtil.close(conn);
      }
      return list;
   }
   
   @Override
   public List<FavoriteDto> selectByAreaCode(long areaCode) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<FavoriteDto> list = new ArrayList<FavoriteDto>();
      try {
         conn = DBUtil.getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("SELECT * FROM User, BaseAddress \n");
         sql.append("WHERE code = ? \n");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setLong(1, areaCode);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            FavoriteDto dto = new FavoriteDto();
            // dto setting
            dto.setCode(areaCode);
            list.add(dto);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBUtil.close(rs);
         DBUtil.close(pstmt);
         DBUtil.close(conn);
      }
      return list;
   }

}