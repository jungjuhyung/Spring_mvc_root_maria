package com.ict.ajax.controller;

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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// Ajax의 Controller는 @RestController를 사용한다.
@RestController
public class AjaxTestController {
	// servlet-context.xml(디스페처 서블릿)로 리턴되지 않고 바로 브라우저에 출력한다.
	// 반환형이 String인 경우 문서타입(contentType) = "text/html" 타입으로 자동으로 처리됨
	// 그러나 까먹을 수 있으니 produces = "text/html; charset=utf-8" 이렇게 작성해주는게 좋다.
	// produces = "text/html; charset=utf-8" 한글 처리해주는 것이다.
	@RequestMapping(value = "test01.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String Text_Exam01() {
		String msg = "<h2>Hello World Spring Ajax!! <br>환영합니다.</h2>";
		return msg;
	}
	
	// xml을 보내려면 produces = "text/xml; charset=utf-8"로 작성해줘야한다.
	@RequestMapping(value = "test02.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam02() {
		StringBuffer sb = new StringBuffer();
		//  \" = " 
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<products>");
        sb.append("<product>");
        sb.append("<name>흰우유</name>");
        sb.append("<price>950</price>");
        sb.append("</product>");
        sb.append("<product>");
        sb.append("<name>딸기우유</name>");
        sb.append("<price>1050</price>");
        sb.append("</product>");
        sb.append("<product>");
        sb.append("<name>초코우유</name>");
        sb.append("<price>1100</price>");
        sb.append("</product>");
        sb.append("<product>");
        sb.append("<name>바나나우유</name>");
        sb.append("<price>1550</price>");
        sb.append("</product>");
        sb.append("</products>");
        return sb.toString();
	}
	
	@RequestMapping(value = "test03.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam03() {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<products>");
        sb.append("<product count=\"5\" name=\"제너시스\" />");
        sb.append("<product count=\"7\" name=\"카렌스\" />");
        sb.append("<product count=\"9\" name=\"카니발\" />");
        sb.append("<product count=\"5\" name=\"카스타\" />");
        sb.append("<product count=\"2\" name=\"트위치\" />");
        sb.append("</products>");
        return sb.toString();
	}
	
	@RequestMapping(value = "test04.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam04() {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<products>");
        sb.append("<product count=\"5\" name=\"제네시스\">현대자동차</product>");
        sb.append("<product count=\"7\" name=\"카렌스\">기아자동차</product>");
        sb.append("<product count=\"9\" name=\"카니발\">기아자동차</product>");
        sb.append("<product count=\"5\" name=\"카스타\">기아자동차</product>");
        sb.append("<product count=\"2\" name=\"트위치\">르노자동차</product>");
        sb.append("</products>");
        return sb.toString();
	}
	
	@RequestMapping(value = "test05.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam05() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			URLConnection conn =url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String msg = null;
			while ((msg=br.readLine()) != null) {
				sb.append(msg);
			}
			return sb.toString();
		} catch (Exception e) {
			
		}
		return null;
	}
	@RequestMapping(value = "test06.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String Json_Exam06() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("{\"name\" : \"HTML\", \"scope\":\"15\"},");
		sb.append("{\"name\" : \"CSS\", \"scope\":\"10\"},");
		sb.append("{\"name\" : \"JavaScript\", \"scope\":\"17\"},");
		sb.append("{\"name\" : \"JSP\", \"scope\":\"13\"}");
		sb.append("]");
		return sb.toString();
	}
	@RequestMapping(value = "test07.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String Json_Exam07() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("https://raw.githubusercontent.com/paullabkorea/coronaVaccinationStatus/main/data/data.json");
			URLConnection conn =url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String msg = null;
			while ((msg=br.readLine()) != null) {
				sb.append(msg);
			}
			return sb.toString();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	// 공공데이터 처리
	@RequestMapping(value = "dustdata.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String dustData() throws IOException {
        try {
        	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
        	/*Service Key*/
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=GX4kjX1Z1eB5QSo187qeWIlnJyW7fxq04WHUQmTuGNpcNFvSaN8rxGhj5pOkfgHRYmXiKnpZxasyxkIWBTm2aw==");
			urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8"));
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
        return null;
    }
	
}
