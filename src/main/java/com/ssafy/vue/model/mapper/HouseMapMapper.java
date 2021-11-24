package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	String getSidoName(String sidoCode) throws SQLException;
	String getGugunName(String gugunCode) throws SQLException;
	String getSidoByCode(String sidoCode) throws SQLException;
	String getCode(String address) throws SQLException;
	List<SidoGugunCodeDto> getGugunByKeyword(String keyword) throws SQLException;
	List<SidoGugunCodeDto> getSidoByKeyword(String keyword) throws SQLException;
}
