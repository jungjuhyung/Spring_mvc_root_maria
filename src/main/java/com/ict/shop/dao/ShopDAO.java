package com.ict.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List<ShopVO> getShopList(String category) throws Exception {
		return sessionTemplate.selectList("shop.shop_list", category);
	}

	public ShopVO getShopDetail(String shop_idx) throws Exception {
		return sessionTemplate.selectOne("shop.shop_detail", shop_idx);
	}

	public CartVO getCartChk(String m_id, String p_num) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("m_id", m_id);
		map.put("p_num", p_num);
		return sessionTemplate.selectOne("shop.cart_chk", map);
	}
	public int getCartInsert(CartVO cartVO) throws Exception {
		return sessionTemplate.insert("shop.cart_insert", cartVO);
	}

	public int getCartUpdate(CartVO cartVO) throws Exception {
		return sessionTemplate.update("shop.cart_update", cartVO);
	}

	public List<CartVO> getCartList(String m_id) throws Exception {
		return sessionTemplate.selectList("shop.cart_list", m_id);
	}
	
	public int getCartEdit(CartVO cavo) throws Exception {
		return sessionTemplate.update("shop.cart_edit", cavo);
	}
	public int getCartDelete(String cart_idx) throws Exception {
		return sessionTemplate.delete("shop.cart_delete", cart_idx );
	}
	
	public int getProductInsert(ShopVO svo) throws Exception {
		return sessionTemplate.insert("shop.product_insert", svo);
	}
}











