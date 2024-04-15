<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
/* 		
		*해당 ajax는 프론트에서 파싱해서 정보를 빼온것이다
		$("#res").empty();
		$.ajax({
			url: "kakaoUser.do",
			method: "post",
			dataType: "json",
			success: function(data) {
				let name = data["properties"].nickname;
				let email = data["kakao_account"].email;
				$("#res").append(name+"님 환영합니다. "+"email :" + email)
			},
			error: function() {
				alert("읽기 실패");
			}
		})
		*/
		$.ajax({
			url: "kakaoUser2.do",
			method: "post",
			dataType: "text",
			success: function(data) {
				let users = data.split("/");
				$("#res").append(users[1] + "(" + users[2] + ")" + "님 환영합니다.")
			},
			error: function() {
				alert("읽기 실패");
			}
		});
	});
</script>
</head>
<body>
	 <h2>Kakao 로그인 결과보기</h2>
	 <div id="res">
	 
	 </div>
	 <!-- 다음 개발자 모드에서 로그아웃 개발 예시참고 -->
	 <!-- 일반 로그아웃과 카카오 계정과 함께 로그아웃 2종류가 있다. -->
	 <a href="https://kauth.kakao.com/oauth/logout?client_id=8d16074c8b0edb74b83d17dc520ff427&logout_redirect_uri=http://localhost:8090/kakaologout.do">
	 로그아웃
	 </a>
</body>
</html>