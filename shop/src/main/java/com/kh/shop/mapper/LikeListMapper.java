package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemDto;

@Component
public class LikeListMapper implements RowMapper<ItemDto>{

	@Override
	public ItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		return ItemDto.builder()
				.itemNo(rs.getInt("item_no"))
				.build();
	}

}
