<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
    span { width: 150px; color: red;}
    input{border:1px solid red;}
    table{width: 80%; margin: 0 auto;}
    table,th,td {border: 1px solid gray; text-align: center;}
    h2{text-align: center;}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#res").empty();
		$.ajax({
			url: "dustdata.do",
			method: "post",
			dataType : "xml",
			success : function(data) {
				console.log(data);
				let table = "<table>";
				table += "<thead><tr><th>지역</th><th>측정소</th><th>미세먼지농도</th></tr></thead>"
				table += "<tbody>";
				$(data).find("item").each(function() {
						let sidoname = $(this).find("sidoName").text()
						let stationame = $(this).find("stationName").text()
						let dust = $(this).find("pm10Value").text()
						table += "<tr>"
						table += "<td>" + sidoname + "</td>";
						table += "<td>" + stationame + "</td>";
						table += "<td>" + dust + "</td>";
						table += "</tr>";
				});
				table += "</tbody>";
				table += "</table>";
				$("#res").append(table);
			},
			error: function() {
				alert("읽기 실패");
			}
	})
})
</script>
</head>
<body>
	<h2>Ajax를 이용한 공공데이터 처리</h2>
	<div id="res">
	</div>
</body>
</html>