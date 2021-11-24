package com.ssafy.vue.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

	@ApiOperation(value = "검색 정보", notes = "검색정보를 반환한다.", response = List.class)
	@GetMapping("/search")
	public ResponseEntity<List<SidoGugunCodeDto>> search(
			@RequestParam("keyword") @ApiParam(value = "검색 키워드.", required = true) String keyword) throws Exception {

		List<SidoGugunCodeDto> list = houseMapService.getGugunByKeyword(keyword);
		for (int j = 0; j < list.size(); j++) {
			SidoGugunCodeDto dto = list.get(j);
			dto.setSidoCode(dto.getGugunCode().substring(0, 2));
			dto.setSidoName(houseMapService.getSidoByCode(dto.getSidoCode()));
		}

		List<SidoGugunCodeDto> sido = houseMapService.getSidoByKeyword(keyword);
		for (int j = 0; j < sido.size(); j++) {
			SidoGugunCodeDto dto = sido.get(j);
			List<SidoGugunCodeDto> gugun = houseMapService.getGugunInSido((dto.getSidoCode()));
			for (int k = 0; k < gugun.size(); k++) {
				SidoGugunCodeDto dto2 = gugun.get(k);
				System.out.println(dto2.getGugunName());
				dto2.setSidoCode(dto2.getGugunCode().substring(0, 2));
				dto2.setSidoName(houseMapService.getSidoByCode(dto2.getSidoCode()));
				list.add(dto2);
			}
		}

		return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/apt", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> apt(@RequestParam("LAWD_CD") String gugun) throws Exception {

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=PdIWX7uJ0xSSI9JZ95aZZauuxtk0z5MSUs1sUq6th8uytplflwZkegSbl4PSkIfWQH9ZQMEVPUI9LoEgksZq%2Fw%3D%3D");
		urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(gugun, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode("202110", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("30", "UTF-8"));

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
			JSONObject xy;
			JSONObject item = items.getJSONObject(i);
			StringBuilder sb = new StringBuilder();
			String gugunName = houseMapService.getAddress(gugun);
			sb.append(gugunName).append(" ");
			if (!item.optString("법정동").equals("") && !item.optString("지번").equals("")) {
				sb.append(item.getString("법정동")).append(" ");
				sb.append(item.get("지번").toString());

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
				xy = new JSONObject(response.toString());
				System.out.println(xy.toString());
				if(xy.getJSONArray("documents").optJSONObject(0) != null) {
					item.put("x", xy.getJSONArray("documents").getJSONObject(0).get("x"));
					item.put("y", xy.getJSONArray("documents").getJSONObject(0).get("y"));
				}

			}else if(!item.optString("도로명").equals("") && !item.optString("도로명건물본번호코드").equals("") && !item.optString("도로명건물부번호코드").equals("")){
				sb.append(item.getString("도로명")).append(" ");
				sb.append(item.getString("도로명건물본번호코드")).append(" ");
				sb.append(item.getString("도로명건물부번호코드"));

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
				String inputLine2;
				StringBuffer response2 = new StringBuffer();
				while ((inputLine2 = in.readLine()) != null) {
					response2.append(inputLine2);
				}
				xy = new JSONObject(response2.toString());
				System.out.println(xy.toString());
				if(xy.getJSONArray("documents").optJSONObject(0) != null) {
					item.put("x", xy.getJSONArray("documents").getJSONObject(0).get("x"));
					item.put("y", xy.getJSONArray("documents").getJSONObject(0).get("y"));
				}
			}else {
				continue;
			}

		}
		rd.close();
		conn.disconnect();
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

	}

	@ApiOperation(value = "좌표 주변 1키로 각종 정보", notes = "해당지역의 각종 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/around", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> around(@RequestParam("x") @ApiParam(value = "경도", required = true) String x,
			@RequestParam("y") @ApiParam(value = "위도", required = true) String y) throws Exception {

		JSONObject ret = new JSONObject("{}");
		ret.put("subway", new JSONObject(houseMapService.getSubway(x, y)).getJSONArray("documents"));
		ret.put("convenience", new JSONObject(houseMapService.getCv(x, y)).getJSONArray("documents"));
		ret.put("cafe", new JSONObject(houseMapService.getCafe(x, y)).getJSONArray("documents"));
		ret.put("bank", new JSONObject(houseMapService.getBank(x, y)).getJSONArray("documents"));
		ret.put("public", new JSONObject(houseMapService.getPublic(x, y)).getJSONArray("documents"));
		ret.put("kindergarden", new JSONObject(houseMapService.getKid(x, y)).getJSONArray("documents"));
		ret.put("ElementarySchool", new JSONObject(houseMapService.getSchoolE(x, y)).getJSONArray("documents"));
		ret.put("MiddleSchool", new JSONObject(houseMapService.getSchoolM(x, y)).getJSONArray("documents"));
		ret.put("HighSchool", new JSONObject(houseMapService.getSchoolH(x, y)).getJSONArray("documents"));
		ret.put("mart", new JSONObject(houseMapService.getMart(x, y)).getJSONArray("documents"));

		if (!houseMapService.getCctv(x, y).equals("")) {
			ret.put("cctv", new JSONObject(houseMapService.getCctv(x, y)).getJSONArray("cctv"));
		} else {
			ret.put("cctv", new JSONArray("[]"));
		}

		return new ResponseEntity<String>(ret.toString(), HttpStatus.OK);
	}

}
