package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemIoDto;

@Component
public class ItemIoMapper implements RowMapper<ItemIoDto>{

	@Override
	public ItemIoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ItemIoDto.builder()
				.itemIoNo(rs.getInt("item_io_no"))
				.itemIoTotal(rs.getInt("item_io_total"))
				.sizeName(rs.getString("size_name"))
				.itemNo(rs.getInt("item_no"))
				.build();
					
	}
	
}
