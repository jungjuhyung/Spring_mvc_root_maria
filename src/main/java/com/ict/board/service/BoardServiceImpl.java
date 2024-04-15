package com.ict.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.board.dao.BoardDAO;
import com.ict.board.dao.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	// 게시글 총 개수
	@Override
	public int getTotalCount() {
		return boardDAO.getTotalCount();
	}
	
	// 게시글 페이징 기법으로 가져오기
	@Override
	public List<BoardVO> getBoardList(int offset, int limit) {
		return boardDAO.getBoardList(offset, limit);
	}
	
	// 게시글 작성
	@Override
	public int getBoardInsert(BoardVO bovo) {
		return boardDAO.getBoardInsert(bovo);
	}
	
	// 조회수 증가
	@Override
	public int getBoardHit(String bo_idx) {
		return boardDAO.getBoardHit(bo_idx);
	}

	// 상세보기
	@Override
	public BoardVO getBoardDetail(String bo_idx) {
		return boardDAO.getBoardDetail(bo_idx);
	}
	
	// 답글 다는 원글의 lev 올리기
	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		return boardDAO.getLevUpdate(map);
	}
	
	// 답글을 DB에 추가
	@Override
	public int getAnsInsert(BoardVO bovo) {
		return boardDAO.getAnsInsert(bovo);
	}
	
	// 게시글(리플 삭제) 삭제
	@Override
	public int getBoardDelete(BoardVO bovo) {
		return boardDAO.getBoardDelete(bovo);
	}
	
	// 게시글 수정
	@Override
	public int getBoardUpdate(BoardVO bovo) {
		return boardDAO.getBoardUpdate(bovo);
	}
}
