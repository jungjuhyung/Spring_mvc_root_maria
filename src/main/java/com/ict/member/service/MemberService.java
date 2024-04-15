package com.ict.member.service;

import java.util.List;

import com.ict.member.dao.MemberVO;

public interface MemberService {
	// 로그인
	public MemberVO getLogin(MemberVO mvo);
	public List<MemberVO> getMemberList();
	public String getAjaxIdChk(String m_id);
	public int getAjaxJoin(MemberVO mvo);
	public int getAjaxDelete(String m_idx);
}
