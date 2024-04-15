<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<!-- crossorigin="anonymous" ??? -->
<!-- summernote 설정하는 javascript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<script src="resources/js/summernote-lite.js"></script>
<script src="resources/js/lang/summernote-ko-KR.js"></script>
<!-- summer note css 링크 -->
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	/* summernote toolbar 수정 */
    .note-btn-group{width: auto;}
    .note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	function WriteOk(f) {
		f.action="gb1_writeok.do"
		f.submit()
	}
</script>
<script type="text/javascript">
function sendImage(file,editor) {
   	console.log("콘솔");
    let frm = new FormData();
    frm.append("s_file", file);
    $.ajax({
        url : "saveImg.do",
        data : frm,
        method : "post",
        contentType : false,
        processData: false,
        cache: false,
        dataType : "json"
    }).done(function(data) {
        let path = data.path;
        let fname = data.fname;
        $("#content").summernote("editor.insertImage", path+"/"+fname)
    });
}

// summernote Editor 사용하기 위해서 라이브러리 다운 받아서 webapp-resources 안에 넣기
// 여기서는 선생님의 파일을 받아서 사용했음
//summernote 설정
$(document).ready(function() {
    $('#content').summernote({
        lang: "ko-KR",                                        //언어
        height: "300",                                        // 에디터 높이
        focus: true,                                        // 에디터 로딩 후 포커스 여부
        placeholder: "최대 3000자까지 쓸 수 있습니다.",   // placeholder
        callbacks : {
            onImageUpload : 
            	function(files, editor) {
            	console.log("하이")
            	for (var i = 0; i < files.length; i++) {
                     sendImage(files[i], editor);    
            	}
            }
        }
    });
});


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
					<td><input type="text" name="name" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td><input type="text" name="subject" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td><input type="text" name="email" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="pwd" size ="20"/></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="20" cols="50" id="content" name="content"></textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" value="저장" onclick="WriteOk(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
<!-- crossorigin="anonymous" ??? -->
<!-- summernote 설정하는 javascript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<script src="resources/js/summernote-lite.js"></script>
<script src="resources/js/lang/summernote-ko-KR.js"></script>

</body>
</html>