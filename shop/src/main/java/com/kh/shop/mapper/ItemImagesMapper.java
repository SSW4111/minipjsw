package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemImagesDto;

@Component
public class ItemImagesMapper implements RowMapper<ItemImagesDto>{

	@Override
	public ItemImagesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ItemImagesDto.builder()
				.itemNo(rs.getInt("item_no"))
				.attachmentNo(rs.getInt("attachment_no"))
				.build();
	}

}
