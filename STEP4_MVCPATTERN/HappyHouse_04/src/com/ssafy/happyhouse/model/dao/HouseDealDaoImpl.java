package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.util.DBUtil;

public class HouseDealDaoImpl implements HouseDealDao {

	private static HouseDealDao houseDealDao;

	private HouseDealDaoImpl() {
	}

	public static HouseDealDao getHouseDealDao() {
		if (houseDealDao == null)
			houseDealDao = new HouseDealDaoImpl();
		return houseDealDao;
	}

	@Override
	public List<HouseDealDto> searchByDong(String dong) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDealDto> list = new ArrayList<HouseDealDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from housedeal ");
			sql.append("where dong LIKE ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + dong + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseDealDto dto = new HouseDealDto();
				dto.setAptName(rs.getString("AptName"));
				dto.setArea(rs.getString("area"));
				dto.setDealAmount(rs.getString("dealAmount"));
				dto.setDealDay(rs.getString("dealDay"));
				dto.setDong(rs.getString("dong"));

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
	public List<HouseDealDto> searchByAptName(String AptName) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDealDto> list = new ArrayList<HouseDealDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from housedeal ");
			sql.append("where AptName like ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + AptName + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseDealDto dto = new HouseDealDto();
				dto.setAptName(rs.getString("AptName"));
				dto.setArea(rs.getString("area"));
				dto.setDealAmount(rs.getString("dealAmount"));
				dto.setDealDay(rs.getString("dealDay"));
				dto.setDong(rs.getString("dong"));

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
	public List<HouseDealDto> listAll() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDealDto> list = new ArrayList<HouseDealDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM housedeal \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseDealDto dto = new HouseDealDto();
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
	public List<HouseDealDto> joinByCode(String dong, String AptName) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDealDto> list = new ArrayList<HouseDealDto>();

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(
					"SELECT hi.buildYear buildYear, hd.AptName AptName, hd.dealAmount dealAmount, hd.area area, hd.floor floor, hd.jibun jibun\n");
			sql.append("FROM housedeal hd\n");
			sql.append("INNER JOIN houseinfo hi\n");
			sql.append("ON hd.code = hi.code AND hd.AptName = hi.AptName\n");
			sql.append("WHERE hi.dong = ? AND hi.AptName = ? \n");
			sql.append("ORDER BY dealAmount");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			pstmt.setString(2, AptName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseDealDto dto = new HouseDealDto();
				dto.setBuildYear(rs.getString("buildYear"));
				dto.setAptName(rs.getString("AptName"));
				dto.setDealAmount(rs.getString("dealAmount"));
				dto.setFloor(rs.getString("floor"));
				dto.setArea(rs.getString("area"));
				dto.setJibun(rs.getString("jibun"));
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
