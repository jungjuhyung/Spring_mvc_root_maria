<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	카카오 developers 로그인 후 내 어플리케이션에서 애플리케이션 선택 한 후 javascript키 복사
		- c186c802b1e519c6f748b4481b8a4b53 => appkey에 사용된다.
	- 카카오 developers -> 상단 제품 메뉴 -> 지도/로컬 선택 -> 아래 문서 보기 클릭 ->
		-> Maps API 클릭 -> Web 클릭 -> 왼쪽 메뉴 sample 클릭 -> 지도 생성하기 클릭
		-> javascript+HTML 메뉴의 body부분 복사
--%>
<!-- 지도를 표시할 div 입니다 -->
<h2>카카오 지도(기본값 : 카카오 본사)</h2>
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c186c802b1e519c6f748b4481b8a4b53"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 
</script>
</body>
</html>