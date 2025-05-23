package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.CartDto;

@Component
public class CartMapper implements RowMapper <CartDto> {

	@Override
	public CartDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CartDto.builder()
				.cartNo(rs.getInt("cart_no"))
				.usersEmail(rs.getString("users_email"))
				.itemNo(rs.getInt("item_no"))
				.cartQty(rs.getInt("cart_qty"))
				.build();
	}
	
}
