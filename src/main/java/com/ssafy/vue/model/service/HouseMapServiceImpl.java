package com.ssafy.vue.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public String getAddress(String gugun) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(sqlSession.getMapper(HouseMapMapper.class).getSidoName(gugun.substring(0, 2)));
		sb.append(" ");
		sb.append(sqlSession.getMapper(HouseMapMapper.class).getGugunName(gugun));
		return sb.toString();
	}
	
	@Override
	public String getCctv(String x, String y) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubway(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/category.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=SW8";
//		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}

	@Override
	public String getCv(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/category.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=CS2";
//		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}

	@Override
	public String getCafe(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/category.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=CE7";
//		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}

	@Override
	public String getBank(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/category.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=BK9";
//		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}

	@Override
	public String getPublic(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/category.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=PO3";
//		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}

	@Override
	public String getKid(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/category.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=PS3";
//		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}

	@Override
	public String getSchoolE(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=20000";
		URL += "&category_group_code=SC4";
		URL += "&query=" + URLEncoder.encode("초등학교", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}
	@Override
	public String getSchoolM(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=SC4";
		URL += "&query=" + URLEncoder.encode("중학교", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}
	@Override
	public String getSchoolH(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=SC4";
		URL += "&query=" + URLEncoder.encode("고등학교", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}

	@Override
	public String getMart(String x, String y) throws Exception {
		String URL = "https://dapi.kakao.com/v2/local/search/category.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&category_group_code=MT1";
//		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");
//		37.514322572335935 127.06283102249932
		String USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;
		obj = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", USER_INFO);
		conn.setRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setDefaultUseCaches(false);
		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return response.toString();
	}


}
