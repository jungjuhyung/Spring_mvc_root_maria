package com.ict.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BbsDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	// 게시판 리스트 조회
	public List<BbsVO> getBbsList() {
		try {
			return sessionTemplate.selectList("bbs.bbslist");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 게시판 입력
	public int getBbsInsert(BbsVO bvo) {
		try {
			return sessionTemplate.insert("bbs.bbsinsert", bvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 게시판 상세보기
	public BbsVO getBbsDetail(String b_idx) {
		try {
			return sessionTemplate.selectOne("bbs.bbsdetail", b_idx);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 게시판 조회수 수정
	public int getHitUpdate(String b_idx) {
		try {
			return sessionTemplate.update("bbs.bbshitupdate", b_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 댓글 리스트 가져오는 메서드
	public List<CommentVO> getCommentList(String b_idx) {
		try {
			return sessionTemplate.selectList("bbs.commentlist", b_idx);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 댓글 추가
	public int getCommentInsert(CommentVO cvo) {
		try {
			return sessionTemplate.insert("bbs.commentinsert", cvo);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 댓글 삭제
	public int getCommentDelete(String c_idx) {
		try {
				int res = sessionTemplate.delete("bbs.commentdelete", c_idx);
				System.out.println(res);
				System.out.println(c_idx);
				return res;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 게시판 총 갯수 구하기
	public int getTotalCount() {
		try {
			return sessionTemplate.selectOne("bbs.count");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 게시판 페이징 기법 리스트
	public List<BbsVO> getBbsList(int offset, int limit) {
		try {
			// DB에 offset과 limit를 추가하던가
			// Map컬렉션에 넣어서 보내면 mybatis가 Map을 인식해서 파라미터로 사용할 수 있다.
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sessionTemplate.selectList("bbs.list", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 원글 삭제 메서드
	public int getBbsDelete(String b_idx) {
		try {
			// return sessionTemplate.delete("bbs.bbsdelete", b_idx);
			return sessionTemplate.update("bbs.bbsdelete", b_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
