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
		function getList() {
			$.ajax({
				url: "getAjaxList.do",
				method: "post",    // type : "post"로 작성할 수도 있다.
				dataType : "xml",
				success : function(data) {
					$("#tbody").empty();
					let tbody;
					$(data).find("member").each(function() {
						tbody += "<tr>"
						tbody += "<td>" + $(this).find("m_idx").text() + "</td>";
						tbody += "<td>" + $(this).find("m_id").text() + "</td>";
						tbody += "<td>" + $(this).find("m_pw").text() + "</td>";
						tbody += "<td>" + $(this).find("m_name").text() + "</td>";
						tbody += "<td>" + $(this).find("m_age").text() + "</td>";
						tbody += "<td>" + $(this).find("m_reg").text() + "</td>";
						tbody += "<td><input type='button' value='삭제' id='del' name='"+ $(this).find("m_idx").text()+"'>"+"</td>";
						tbody += "</tr>";
					});
					$("#tbody").append(tbody);
				},
				error: function() {
					alert("읽기 실패");
				}
			});
		};
		
		$("#m_id").keyup(function() {
			$.ajax({
				url: "getAjaxIdChk.do",
				data : "m_id="+$("#m_id").val(),
				method: "post",
				dataType : "text",
				success : function(data) {
					if (data=="1") {
						// 중복 없음(사용 가능)
						$("#join_btn").removeAttr("disabled")
						$("span").text("사용 가능")
					}else if (data=="0") {
						// 중복 있음(사용 불가능)
						$("#join_btn").attr("disabled")
						$("span").text("사용 불가능")
					}
				},
				error: function() {
					alert("읽기 실패");
				}
			})
		});
		
		// 데이터가 여러개일 때는 직렬화를 사용한다.
		// serialize() => 직렬화, form 태그 안에 있는 요소만 가능하다.
		// button태그는 누르면 submit으로 처리된다.
		// input typr=button은 그냥 누르는 효과이다.
		$("#join_btn").click(function() {
			let param = $("#myform").serialize();
			$.ajax({
				url: "getAjaxJoin.do",
				data : param,
				method: "post",
				dataType : "text",
				success : function(data) {
					if (data == 0) {
						alert("가입 실패");
					}else if (data == 1) {
						$("#tbody").empty();
						getList();
					}
				},
				error: function() {
					alert("연결 실패")
				}
			})
		})
		
		// 하나의 id나 클래스를 가진 여러 버튼을 클릭하는 이벤트는 ajax에서는 안되기 때문에
		// 아래와 같이 작성해야한다.
		$("table").on("click","#del", function() {
			$.ajax({
				url: "getAjaxDelete.do",
				data : "m_idx="+$(this).attr("name"),
				method: "post",
				dataType : "text",
				success : function(data) {
					if (data == 0) {
						alert("삭제 실패");
					}else if (data == 1) {
						$("#tbody").empty();
						getList();
					}
				},
				error: function() {
					alert("연결 실패")
				}
			})
		})
		
		getList();
	})
</script>
</head>
<body>
	<h2>Ajax를 이용한 DB처리</h2>
	<h2> 회원 정보 입력하기 </h2>
    <form name="myform" method="post" id="myform" autocomplete="off">
        <table>
            <thead>
                <tr>
                    <th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <input type="text" size="14" name="m_id" id="m_id" ><br><span>중복여부</span>
                    </td>
                    <td> <input type="password" size="8" name="m_pw" ></td>  
                    <td> <input type="text" size="14" name="m_name" ></td>  
                    <td> <input type="text" size="25" name="m_age" ></td>  
                </tr>
            </tbody>
            <tfoot>
                <tr><td colspan="7" align="center"><input type="button" id="join_btn" value="가입하기" disabled></td></tr>
            </tfoot>
        </table>
    </form>
    <br /> <br /> <br />
    <h2> 회원 정보 보기 </h2>
    <div>
        <table id="list">
            <thead>
                <tr>
                    <th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th><th>가입일</th><th>삭제</th>
                </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>
</body>
</html>