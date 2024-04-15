<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	.note-btn-group{width: auto;}
    .note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	function delete_go(f) {
		f.action="gb1_delete.do";
		f.submit();
	}
	function update_go(f) {
		f.action="gb1_update.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 작성화면</h2>
		<hr />
		<p>[<a href="gb1_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${gvo.name}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td>${gvo.subject}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${gvo.email}</td>
				</tr>
				<tr style="text-align: left;">
					<td colspan="2">
						<%-- <pre style="padding-left: 15px;">${gvo.content}</pre> --%>
						<textarea rows="20" cols="50" id="content" name="content">${gvo.content}</textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" name="idx" value="${gvo.idx}">
							<input type="button" value="수정" onclick="update_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="삭제" onclick="delete_go(this.form)" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
<!-- crossorigin="anonymous" ??? -->
<!-- summernote 설정하는 javascript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<script src="resources/js/summernote-lite.js"></script>
<script src="resources/js/lang/summernote-ko-KR.js"></script>

<!-- 실제 텍스트에리어를 변화시키는 javascript -->
<script type="text/javascript">
 	$(document).ready(function() {
		$("#content").summernote({
			lang: "ko-KR",					// 한글 설정
			height: 300,                 // 에디터 높이
			focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
			placeholder: '최대3000자까지 쓸 수 있습니다'//placeholder 설정
			
		});
		// summernote 양식을 보여주고 비활성화
		$('#content').summernote('disable');
	})
</script>
</html>