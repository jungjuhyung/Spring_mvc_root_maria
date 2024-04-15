package com.ict.guestbook.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GB_DAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List<GB_VO> getGuestList() {
		try {
			return sessionTemplate.selectList("guestbook.list");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getGuestInsert(GB_VO gvo) {
		try {
			return sessionTemplate.insert("guestbook.insert", gvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public GB_VO getDetail(String idx) {
		try {
			return sessionTemplate.selectOne("guestbook.selectone", idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public int getGuestDelete(String idx) {
		try {
			return sessionTemplate.delete("guestbook.delete", idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	public int getGuestUpdate(GB_VO gvo) {
		try {
			return sessionTemplate.update("guestbook.update", gvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
