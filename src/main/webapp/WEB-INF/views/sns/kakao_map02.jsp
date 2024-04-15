<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c186c802b1e519c6f748b4481b8a4b53"></script>
<script type="text/javascript">
	// 위도와 경도 구하기
	navigator.geolocation.getCurrentPosition(function(position) {
		let lat = position.coords.latitude;
		let lng = position.coords.longitude;
		geo_map(lat,lng);
	});
</script>
</head>
<body>
<h2>카카오 지도(내 위치)</h2>
<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:100%;height:350px;"></div>
<script>
function geo_map(lat, lng) {
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption); 
}
</script>
</body>
</html>