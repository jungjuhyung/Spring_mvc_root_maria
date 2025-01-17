<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<c:choose>
			<c:when test="${idx==1}"><h1>사원 검색</h1></c:when>
			<c:when test="${idx==2}"><h1>이름 검색(${searchlist.size()} 명)</h1></c:when>
			<c:when test="${idx==3}"><h1>성별 검색(${searchlist.size()} 명)</h1></c:when>
			<c:when test="${idx==4}"><h1>생년월일 검색(${searchlist.size()} 명)</h1></c:when>
		</c:choose>
	</header>
	<article>
		<table>
			<thead>
				<tr><th>사원번호</th><th>이름</th><th>성별</th><th>생년월일</th><th>입사일</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty searchlist}">
						<tr>
							<td colspan="5"><h3>검색한 자료가 존재하지 않습니다.</h3></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${searchlist}">
							<tr>
								<td>${k.emp_no}</td>
								<td>${k.first_name}</td>
								<td>${k.gender}</td>
								<td>${k.birth_date}</td>
								<td>${k.hire_date}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</article>
</body>
</html>