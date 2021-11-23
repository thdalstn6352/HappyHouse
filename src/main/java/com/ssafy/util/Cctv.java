package com.ssafy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Cctv {

	public static String getCctv(String x, String y, String parm) throws Exception {
		
		String strUrl = "http://api.data.go.kr/openapi/tn_pubr_public_cctv_api";
		strUrl += "?serviceKey=PdIWX7uJ0xSSI9JZ95aZZauuxtk0z5MSUs1sUq6th8uytplflwZkegSbl4PSkIfWQH9ZQMEVPUI9LoEgksZq%2Fw%3D%3D";
		strUrl += "&type=json";
		strUrl += "&pageNo=1";
		strUrl += "&numOfRows=500";
		strUrl += "&institutionNm=" + URLEncoder.encode(parm, "UTF-8");
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer urlString = new StringBuffer();
		String current;
		while ((current = in.readLine()) != null) {
			urlString.append(current);
		}
		
		return urlString.toString();
	}
}
