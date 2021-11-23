package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	String getAddress(String gugun) throws Exception;
	
	String getSubway(String x, String y)throws Exception;
	String getCv(String x, String y)throws Exception;
	String getCafe(String x, String y)throws Exception;
	String getBank(String x, String y)throws Exception;
	String getPublic(String x, String y)throws Exception;
	String getCctv(String x, String y)throws Exception;
	String getKid(String x, String y)throws Exception;
	String getSchoolE(String x, String y)throws Exception;
	String getSchoolM(String x, String y)throws Exception;
	String getSchoolH(String x, String y)throws Exception;
	String getMart(String x, String y)throws Exception;
	
	
}
