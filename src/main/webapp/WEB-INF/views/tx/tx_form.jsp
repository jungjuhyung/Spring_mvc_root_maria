<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function result_go(f) {
		f.countnum.value = f.amount.value;
		f.action = "transaction_ok.do";
		f.submit();
	}
</script>
</head>
<body>
	<!--
		트렌젝션 : 쪼갤 수 없는 업무의 최소 단위를 뜻한다.
			-데이터베이스 서버에 여러 개의 클라이언트가 동시에 엑세스 하거나 응용프로그램이 갱신을 처리하는 과정에서
			 중단 될 수 있는 경우 등 데이터 부정합을 방지하고자 할 때 사용한다.
			-데이터 베이스 처리할 때 여러 개의 작업을 하나의 단위로 묶어서 처리하는 것.
			-트렌젝션 처리가 정상적으로 완료된 경우 커밋(commit)을 하고, 오류가 발생할 경우 원래 상태로 롤백(rollback)한다.
				=> 전체가 수행되거나 전체가 수행되지 않는다.
		
		root-context.xml에 spring transation managet를 설정해야한다.
		
		* 예제용 DB 테이블 설정
		create table card(
			customerId   varchar(4000),
        	amount   varchar(4000)
		);
		create table ticket(
			customerId   varchar(4000),
			countnum   varchar(4000) check(countnum <5) 
		);
		티켓 구매수를 5개 미만으로 구매해야 된다.
		5개 이상이 구매하면 트랜잭션 처리를 해야 된다.  
			
	-->
	<h2> 결재 하기 </h2>
    <form method="post">
        <p> 고객 ID : <input type="text" name="customerId"></p>
        <p> 티켓 구매수 : <input type="number" name="amount"></p>
        <input type="hidden" name="countnum" value="">
        <input type="button" value="구매하기" onclick="result_go(this.form)">
        <input type="reset" value="취소하기">
    </form>
</body>
</html>