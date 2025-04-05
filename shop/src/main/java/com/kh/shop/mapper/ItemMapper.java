package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemDto;

@Component
public class ItemMapper implements RowMapper<ItemDto> {

	@Override
	public ItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ItemDto.builder()
				.itemNo(rs.getInt("item_no"))
				.itemColor(rs.getString("item_color"))
				.itemSize(rs.getString("item_size"))
				.itemTitle(rs.getString("item_title"))
				.itemContent(rs.getString("item_content"))
				.itemGender(rs.getString("item_gender"))
				.itemCategory(rs.getString("item_category"))
				.itemDetail(rs.getString("item_detail"))
				.itemLike(rs.getInt("item_like"))
				.build();
	}

}
