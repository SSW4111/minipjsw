package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//장바구니 넣기((좋아요)) //장바구니 버튼 클릭시 들어가는것만됨 삭제는 리스트에서만
	public void cartInsert(int itemNo, String usersEmail) {
		String sql = "insert into cart(item_no, users_email) values(?, ?)";
		Object[] data = {itemNo, usersEmail};
		jdbcTemplate.update(sql,data);
	}
	
	//장바구니 itemNo list
	public List<Integer>cartList(String usersEmail){
		String sql="select item_no from cart where users_email =?";
		Object[] data = {usersEmail};
		return jdbcTemplate.queryForList(sql,Integer.class, data);
	}
	
	//장바구니 아이템 삭제((좋아요해제))
	public boolean deleteCart(int itemNo, String usersEmail) {
		String sql = "delete from cart where item_no=? and users_email=?";
		Object[] data = {itemNo, usersEmail};
		return jdbcTemplate.update(sql,data)>0;
		}
}
