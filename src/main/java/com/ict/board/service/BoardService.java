package com.ict.board.service;

import java.util.List;
import java.util.Map;

import com.ict.board.dao.BoardVO;

public interface BoardService {
	// 게시물 총 갯수
	public int getTotalCount();
	
	// 게시물 전체 불러오기
	public List<BoardVO> getBoardList(int offset, int limit);
	
	// 게시물 삽입
	public int getBoardInsert(BoardVO bovo);
	
	// 게시물 조회수 증가
	public int getBoardHit(String bo_idx);
	
	// 게시물 상세보기
	public BoardVO getBoardDetail(String bo_idx);
	
	// 원글의 lev 증가
	public int getLevUpdate(Map<String, Integer> map);
	
	// 게시물 리플 삽입
	public int getAnsInsert(BoardVO bovo);
	
	// 게시글(리플 포함) 삭제
	public int getBoardDelete(BoardVO bovo);
	
	// 게시글 수정
	public int getBoardUpdate(BoardVO bovo);
}
