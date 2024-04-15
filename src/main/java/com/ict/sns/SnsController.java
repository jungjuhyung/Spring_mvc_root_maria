package com.ict.sns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.ict.sns.kakao.KakaoVO;

@Controller
public class SnsController {
	@Autowired
	private AddrDAO addrDAO;
	
	@RequestMapping("spring_sns_go.do")
	public ModelAndView getSpringSnsGo() {
		return new ModelAndView("sns/loginForm");
	}
	
	// 카카오 로그인 인증코드 및 토큰 받기
	@RequestMapping("kakaologin.do")
	public ModelAndView kakaoLogIn(HttpServletRequest request) {
		// 1. 인증코드 받기
		// 카카오 developer 페이지에서 확인할 수 있다.
		String code = request.getParameter("code");
		// System.out.println("code : " + code);
		
		// 2. 토큰 받기(인증 코드 필요)
		String reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// post 요청 처리
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// 카카오 developer 페이지의 로그인 REST API 항목에서 확인할 수 있다.
			// 헤더 요청 Content-type	= application/x-www-form-urlencoded;charset=utf-8
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// 본문에 4가지 값 넣기
			BufferedWriter bw = 
					new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			
			
			// append 로 값을 추가할 때는 2번째부터 & 를 앞에 붙이고 시작해야한다.
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=8d16074c8b0edb74b83d17dc520ff427");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
			sb.append("&code="+code);
			
			bw.write(sb.toString());
			bw.flush();
			
			// 결과 코드가 200이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			// 토큰 요청을 통한 결과는 JSON 타입
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			StringBuffer sb2 = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb2.append(line);
			}
			String res = sb2.toString();
			// 토큰 응답이 잘 왔는지 sysout으로 찍어보고 해당 내용을 복사해놓자
			// JSON 형식으로 되어있다.
			System.out.println(res);
			
			// 3. 사용자 정보 받기(token을 이용해서 사용자 정보를 받자)
			// 받은 정보는 session에 저장해서 사용(ajax를 사용해서 사용자 정보를 가져온다.)
			
			// String의 JSON 내용을 KakaoVO를 만들어서 Gson을 통해 저장한다.
			Gson gson = new Gson();
			KakaoVO kvo = gson.fromJson(res, KakaoVO.class);
			System.out.println(kvo.getAccess_token());
			System.out.println(kvo.getRefresh_token());
			System.out.println(kvo.getToken_type());
			request.getSession().setAttribute("access_token", kvo.getAccess_token());
			request.getSession().setAttribute("refresh_token",kvo.getRefresh_token());
			request.getSession().setAttribute("token_type",kvo.getToken_type());
			System.out.println("오냐");
			
			return new ModelAndView("sns/result");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return new ModelAndView("common/error");
	}
	
	// 네이버 로그인 인증코드 및 토큰 받기
		@RequestMapping("naverlogin.do")
		public ModelAndView naverLogIn(HttpServletRequest request) {
			// 1. 인증코드 받기
			// 카카오 developer 페이지에서 확인할 수 있다.
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			// System.out.println("code : " + code);
			
			// 2. 토큰 받기(인증 코드 필요)
			String reqURL = "https://nid.naver.com/oauth2.0/token";
			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				// post 요청 처리
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				
				// 카카오 developer 페이지의 로그인 REST API 항목에서 확인할 수 있다.
				// 헤더 요청 Content-type	= application/x-www-form-urlencoded;charset=utf-8
				
				// 본문에 4가지 값 넣기
				BufferedWriter bw = 
						new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				
				
				// append 로 값을 추가할 때는 2번째부터 & 를 앞에 붙이고 시작해야한다.
				StringBuffer sb = new StringBuffer();
				sb.append("grant_type=authorization_code");
				sb.append("&client_id=HaIYXsmytrRSKSwlaqbX");
				sb.append("&client_secret=4YbaQjXLpZ");
				sb.append("&state=test");
				sb.append("&code="+code);
				
				bw.write(sb.toString());
				bw.flush();
				
				// 결과 코드가 200이면 성공
				int responseCode = conn.getResponseCode();
				System.out.println(responseCode);
				// 토큰 요청을 통한 결과는 JSON 타입
				BufferedReader br = 
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String res = sb2.toString();
				// 토큰 응답이 잘 왔는지 sysout으로 찍어보고 해당 내용을 복사해놓자
				// JSON 형식으로 되어있다.
				System.out.println(res);
				
				// 3. 사용자 정보 받기(token을 이용해서 사용자 정보를 받자)
				// 받은 정보는 session에 저장해서 사용(ajax를 사용해서 사용자 정보를 가져온다.)
				
				// String의 JSON 내용을 KakaoVO를 만들어서 Gson을 통해 저장한다.
				Gson gson = new Gson();
				KakaoVO kvo = gson.fromJson(res, KakaoVO.class);
				request.getSession().setAttribute("access_token", kvo.getAccess_token());
				request.getSession().setAttribute("refresh_token",kvo.getRefresh_token());
				request.getSession().setAttribute("token_type",kvo.getToken_type());
				
				return new ModelAndView("sns/result2");
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return new ModelAndView("common/error");
		}
		
	@RequestMapping("kakao_map01.do")
	public ModelAndView KakaoMap01() {
		return new ModelAndView("sns/kakao_map01");
	}
	@RequestMapping("kakao_map02.do")
	public ModelAndView KakaoMap02() {
		return new ModelAndView("sns/kakao_map02");
	}
	@RequestMapping("kakao_map03.do")
	public ModelAndView KakaoMap03() {
		return new ModelAndView("sns/kakao_map03");
	}
	@RequestMapping("kakao_map04.do")
	public ModelAndView KakaoMap04() {
		return new ModelAndView("sns/kakao_map04");
	}
	@RequestMapping("kakao_addr.do")
	public ModelAndView KakaoAddr() {
		return new ModelAndView("sns/kakao_addr");
	}
	@RequestMapping("kakao_addr_ok.do")
	public ModelAndView KakaoAddrOK(AddrVO addrvo) {
		try {
			// DB에 등록
			int result = 0;
			result = addrDAO.addr_Insert(addrvo);
			if (result > 0) {
				return new ModelAndView("sns/loginForm");
			}
		} catch (Exception e) {
			System.out.println();
		}
		return new ModelAndView("common/error");
	}
	
	@RequestMapping("dynamic_query.do")
	public ModelAndView dynamicQuery() {
		return new ModelAndView("emp/dynamicQuery");
	}
}
