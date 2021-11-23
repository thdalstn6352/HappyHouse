package com.ssafy.vue.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService houseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping(value = "/apt", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> apt(@RequestParam("LAWD_CD") String gugun) throws Exception {

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=PdIWX7uJ0xSSI9JZ95aZZauuxtk0z5MSUs1sUq6th8uytplflwZkegSbl4PSkIfWQH9ZQMEVPUI9LoEgksZq%2Fw%3D%3D");
		urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(gugun, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode("202110", "UTF-8"));

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/text");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuffer result = new StringBuffer();
		String line;

		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		JSONObject jsonObject = XML.toJSONObject(result.toString());

		String GEOCODE_URL = "http://dapi.kakao.com/v2/local/search/address.json?query=";
		String GEOCODE_USER_INFO = "KakaoAK 7a84c263a1e8243f9ad885d44c730922";
		URL obj;

		JSONArray items;
		try {
			items = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items")
					.getJSONArray("item");
		} catch (JSONException e) {
//			e.printStackTrace();
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}

		for (int i = 0; i < items.length(); i++) {

			JSONObject item = items.getJSONObject(i);
			StringBuilder sb = new StringBuilder();
			String gugunName = houseMapService.getAddress(gugun);
			sb.append(gugunName).append(" ");
			sb.append(item.getString("법정동")).append(" ");
			sb.append(item.get("지번").toString());

//			System.out.println(sb.toString());

			String address = URLEncoder.encode(sb.toString(), "UTF-8");
			obj = new URL(GEOCODE_URL + address);
			conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", GEOCODE_USER_INFO);
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
			JSONObject xy = new JSONObject(response.toString());

			if (xy.getJSONObject("meta").get("total_count").equals((Integer) 0)) {
				StringBuilder ss = new StringBuilder();
				ss.append(gugunName).append(" ");
				ss.append(item.getString("도로명")).append(" ");
				ss.append(item.getString("도로명건물본번호코드")).append(" ");
				ss.append(item.getString("도로명건물부번호코드"));

				address = URLEncoder.encode(ss.toString(), "UTF-8");
				obj = new URL(GEOCODE_URL + address);
				conn = (HttpURLConnection) obj.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", GEOCODE_USER_INFO);
				conn.setRequestProperty("content-type", "application/json");
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				conn.setDefaultUseCaches(false);
				charset = Charset.forName("UTF-8");
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
				String inputLine2;
				StringBuffer response2 = new StringBuffer();
				while ((inputLine2 = in.readLine()) != null) {
					response2.append(inputLine2);
				}
				xy = new JSONObject(response2.toString());
			}

			item.put("x", xy.getJSONArray("documents").getJSONObject(0).get("x"));
			item.put("y", xy.getJSONArray("documents").getJSONObject(0).get("y"));

		}
		rd.close();
		conn.disconnect();
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

	}

	@ApiOperation(value = "cctv 정보", notes = "해당지역의 cctv정보를 반환한다.", response = List.class)
	@GetMapping("/cctv")
	public ResponseEntity<String> cctv(@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido)
			throws Exception {

		String strUrl = "http://api.data.go.kr/openapi/tn_pubr_public_cctv_api";
		strUrl += "?serviceKey=PdIWX7uJ0xSSI9JZ95aZZauuxtk0z5MSUs1sUq6th8uytplflwZkegSbl4PSkIfWQH9ZQMEVPUI9LoEgksZq%2Fw%3D%3D";
		strUrl += "&type=xml";
		strUrl += "&s_page=1";
		strUrl += "&s_list=1";
		strUrl += "&instt_nm=광진구청";
		URL url = new URL(strUrl);
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection) {
			connection = (HttpURLConnection) urlConnection;
		} else {
			System.out.println("error");

		}
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String urlString = "";
		String current;
		while ((current = in.readLine()) != null) {
			urlString += current + "\n";
		}
		System.out.println(urlString);

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@ApiOperation(value = "지하철역 정보", notes = "해당지역의 지하철역 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/subway", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> subway(@RequestParam("x") @ApiParam(value = "경도", required = true) String x,
			@RequestParam("y") @ApiParam(value = "위도", required = true) String y)
			throws Exception {

		String URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
		URL += "?y=" + y;
		URL += "&x=" + x;
		URL += "&radius=1000";
		URL += "&query=" + URLEncoder.encode("지하철역", "UTF-8");;
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
		
		JSONObject xy = new JSONObject(response.toString());

//		System.out.println(xy.toString());
		return new ResponseEntity<String>(xy.toString(), HttpStatus.OK);
	}
	
	
}
