package com.ict.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	// 게시글 총갯수
	public int getTotalCount() {
		try {
			return sessionTemplate.selectOne("board.count");
		} catch (Exception e) {
			
		}
		return -1;
	}
	
	// 페이징기법 게시글 리스트 가져오기
	public List<BoardVO> getBoardList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sessionTemplate.selectList("board.board_list", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 게시글 작성
	public int getBoardInsert(BoardVO bovo) {
		try {
			return sessionTemplate.insert("board.board_insert", bovo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 조회수 증가
	public int getBoardHit(String bo_idx) {
		try {
			return sessionTemplate.update("board.board_hit", bo_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 상세보기
	public BoardVO getBoardDetail(String bo_idx) {
		try {
			return sessionTemplate.selectOne("board.board_detail", bo_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// 원글 lev 증가
	public int getLevUpdate(Map<String, Integer> map) {
		try {
			return sessionTemplate.update("board.lev_update", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 리플 삽입
	public int getAnsInsert(BoardVO bovo) {
		try {
			return sessionTemplate.insert("board.ans_insert", bovo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 게시글 삭제
	public int getBoardDelete(BoardVO bovo) {
		try {
			if (bovo.getStep().equals("0")) {
				return sessionTemplate.update("board.board_delete", bovo);	
			}else {
				return sessionTemplate.delete("board.board_ans_delete", bovo);	
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 게시글 수정
	public int getBoardUpdate(BoardVO bovo) {
		try {
			return sessionTemplate.update("board.board_update", bovo);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
