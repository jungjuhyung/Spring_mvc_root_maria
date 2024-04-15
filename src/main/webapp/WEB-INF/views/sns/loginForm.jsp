<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function kakao_map01() {
		location.href="kakao_map01.do";
	}
	function kakao_map02() {
		location.href="kakao_map02.do";
	}
	function kakao_map03() {
		location.href="kakao_map03.do";
	}
	function kakao_map04() {
		location.href="kakao_map04.do";
	}
	function kakao_addr() {
		location.href="kakao_addr.do";
	}
</script>
</head>
<body>
	<h1>sns 로그인</h1>
	<%-- 
		kakao 로그인
		1. https://developers.kakao.com/ 접속 로그인 하기
		2. 내 어플리케이션 클릭 후 어플 추가하기(이때 이미지를 등록해야만 개인 개발자 등록 가능)
		3. 추가한 어플로 들어가기
			- REST API 키 복사하기(8d16074c8b0edb74b83d17dc520ff427)
			- 플랫폼 설정하기 : IOS, Android, WEB 중 선택(수업 시간에는 WEB 선택)
			- 사이트 도메인 입력(http://localhost:8090)
				** 원래는 https:// 도메인(회사 IP 또는 회사 주소) 입력
			- Redirect URI 등록 : 카카오에사 내 사이트로 결과들을 보내는 주소
				1. 활성화 상태 : on
				2. Redirect URI 등록(우리가 controller로 보낼 때 사용하는 주소)
					**ex) http://localhost:8090/kakaologin.do
				3. 왼쪽 제품 설명 메뉴 -> 동의 항목 에서 개인정보 동의항목 심사 신청 -> 개인 개발자 신청
		4. 상단 문서 메뉴 -> 카카오 로그인 항목 클릭
			1. 이해하기 읽기(그림)
			2. 설정하기 읽기
			3. REST API -> 리소스 다운로드 -> 축약형과 완성형 중 선택 해서 "전체 다운로드"
				**같이 동봉된 psd파일은 포토샵 파일로 이미지를 수정할 때 사용할 수 있다.
			4. 다운로드한 이미지를 resources 폴더에 넣기
		
		5. 카카오 로그인 요청 - 인가코드 받기 요청
			- 상단 문서 메뉴 -> 카카오 로그은 항목 -> REST API -> 인가코드 URL + 요청 파라미터의 필수 3가지를 get방식 태그 또는 javascript로 보내기
		 <a href="https://kauth.kakao.com/oauth/authorize?client_id=8d16074c8b0edb74b83d17dc520ff427&redirect_uri=http://localhost:8090/kakaologin.do&response_type=code">
	 	 </a>
	 	 
	 	 6. controller에서 요청에 대한 응답 받기 -> 여기부터는 SnsController에서 작업
	 	 
	 	 7. 
	 --%>
	 <!-- 카카오 로그인 -->
	 <a href="https://kauth.kakao.com/oauth/authorize?client_id=8d16074c8b0edb74b83d17dc520ff427&redirect_uri=http://localhost:8090/kakaologin.do&response_type=code">
	 	<img src="resources/images/kakao_login_medium.png">
	 </a>
	 <!-- 네이버 로그인 -->
	 <a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=HaIYXsmytrRSKSwlaqbX&redirect_uri=http://localhost:8090/naverlogin.do&state=test">
	 	<img src="resources/images/naver_btn_login.png">
	 </a>
	 
	 <hr>
	 <button type="button" onclick="kakao_map01()">카카오 지도 연습 01</button>
	 <button type="button" onclick="kakao_map02()">카카오 지도 연습 02</button>
	 <button type="button" onclick="kakao_map03()">카카오 지도 연습 03</button>
	 <button type="button" onclick="kakao_map04()">카카오 지도 연습 04</button>
	 <button type="button" onclick="kakao_addr()">다음 주소 API</button>
</body>
</html>