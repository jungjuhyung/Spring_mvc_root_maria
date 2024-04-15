package com.ict.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ict.bbs.dao.BbsDAO;
import com.ict.bbs.dao.BbsVO;
import com.ict.bbs.dao.CommentVO;

public interface BbsService {
	// 리스트
	public List<BbsVO> getBbsList();
	
	// 삽입
	public int getBbsInsert(BbsVO bvo);
	
	// 상세보기
	public BbsVO getBbsDetail(String idx);
	
	// 조회수 증가
	public int getHitUpdate(String idx);
	
	// 원글 삭제
	public int getBbsDelete(String idx);
	
	// 원글 수정
	public int getBbsupdate(BbsVO bvo);
	
	//--------------아래는 나중에 할 새로운 Service들
	
	// 조회수 업데이트
	
	// 페이징 처리 - 전체 계시물의 수
	public int getTotalCount();
	
	// 페이징 처리를 위한 리스트
	public List<BbsVO> getBbsList(int offset, int limit);
	
	// 댓글 가져오기
	public List<CommentVO> getCommentList(String b_idx);
	
	// 댓글 삽입
	public int getCommentInsert(CommentVO cvo);
	
	// 댓글 삭제
	public int getCommentDelete(String c_idx);
}
