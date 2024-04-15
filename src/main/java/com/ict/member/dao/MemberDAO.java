package com.ict.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public MemberVO getLogin(MemberVO mvo) {
		try {
		return sessionTemplate.selectOne("member.login", mvo);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public List<MemberVO> getMemberList() {
		try {
			return sessionTemplate.selectList("member.ajax_list");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public String getAjaxIdChk(String m_id) {
		try {
			int result = sessionTemplate.selectOne("member.idchk", m_id);
			// m_id가 존재하면
			if (result > 0) {
				return "0";
			}
			// m_id가 존재하지 않으면
			return "1";
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}
	
	public int getAjaxJoin(MemberVO mvo) {
		try {
			return sessionTemplate.insert("member.join", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0; 
	}
	
	public int getAjaxDelete(String m_idx) {
		try {
			return sessionTemplate.delete("member.delete", m_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
