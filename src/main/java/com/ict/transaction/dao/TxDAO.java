package com.ict.transaction.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Repository
public class TxDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public int getTxInsert(TxVO txvo) throws Exception {
		/*
		트렌젝션 처리 이전
		이렇게하면 첫번째 테이블은 수정 후 commit되었지만 2번째 테이블은 수정되지 않아서 서로 값이 달라진다.
		int result = 0;
		result += sqlSessionTemplate.insert("card.cardInsert", txvo);
		result += sqlSessionTemplate.insert("card.ticketInsert", txvo);
		return result;
		*/
		
		// 아래는 트렌젝션 처리
		// 둘 중 하나라도 오류가 나면 롤벡해준다.
		// 오토커밋이 되지 않음
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.insert("card.cardInsert", txvo);
			result += sqlSessionTemplate.insert("card.ticketInsert", txvo);
			transactionManager.commit(status);
			System.out.println("결제성공, 발권성공");
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("결제취소, 발권취소");
			System.out.println(e);
		}
		return -1;
	}

}
