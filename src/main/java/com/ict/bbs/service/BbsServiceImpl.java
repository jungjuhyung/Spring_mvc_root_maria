package com.ict.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.bbs.dao.BbsDAO;
import com.ict.bbs.dao.BbsVO;
import com.ict.bbs.dao.CommentVO;

@Service
public class BbsServiceImpl implements BbsService {
	@Autowired
	private BbsDAO dao;
	
	// 전체 게시글 리스트 가져오기
	@Override
	public List<BbsVO> getBbsList() {
		return dao.getBbsList();
	}
	
	// 게시글 추가
	@Override
	public int getBbsInsert(BbsVO bvo) {
		return dao.getBbsInsert(bvo);
	}

	// 게시글 상세보기
	@Override
	public BbsVO getBbsDetail(String b_idx) {
		return dao.getBbsDetail(b_idx);
	}
	
	// 조회수 올라가는 메서드
	@Override
	public int getHitUpdate(String b_idx) {
		return dao.getHitUpdate(b_idx);
	}
	
	// 댓글 처리 메서드
	@Override
	public List<CommentVO> getCommentList(String b_idx) {
		return dao.getCommentList(b_idx);
	}
	
	// 댓글 추가
	@Override
	public int getCommentInsert(CommentVO cvo) {
		return dao.getCommentInsert(cvo);
	}
	
	// 댓글 삭제
	@Override
	public int getCommentDelete(String c_idx) {
		return dao.getCommentDelete(c_idx);
	}
	
	// 게시물 총 갯수 메서드
	@Override
	public int getTotalCount() {
		return dao.getTotalCount();
	}
	
	// 페이징 기법용 리스트 구하기
	@Override
	public List<BbsVO> getBbsList(int offset, int limit) {
		return dao.getBbsList(offset, limit);
	}
	
	// 게시판 삭제 메서드
	@Override
	public int getBbsDelete(String b_idx) {
		return dao.getBbsDelete(b_idx);
	}
	
	// 게시판 업데이트 삭제 메서드(미구현)
	@Override
	public int getBbsupdate(BbsVO bvo) {
		return 0;
	}



}
