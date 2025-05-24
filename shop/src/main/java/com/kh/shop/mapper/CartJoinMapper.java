package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import com.kh.shop.vo.CartJoinVO;

@Component
public class CartJoinMapper implements RowMapper<CartJoinVO>{

	@Override
	public CartJoinVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CartJoinVO.builder()
				.cartNo(rs.getInt("cart_no"))
				.usersEmail(rs.getString("users_email"))
				.itemNo(rs.getInt("item_no"))
				.itemIoNo(rs.getInt("item_io_no"))
				.cartQty(rs.getInt("cart_qty"))
				.itemTitle(rs.getString("item_title"))
				.itemGender(rs.getString("item_gender"))
				.itemCategory(rs.getString("item_category"))
				.itemDetail(rs.getString("item_detail"))
				.itemLike(rs.getInt("item_like"))
				.itemColor(rs.getString("item_color"))
				.itemPrice(rs.getInt("item_price"))
				.itemContent(rs.getString("item_content"))
				.sizeName(rs.getString("size_name"))
				.build();
	}


	

}
