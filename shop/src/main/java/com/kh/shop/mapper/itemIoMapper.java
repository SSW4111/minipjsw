package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemIoDto;

@Component
public class itemIoMapper implements RowMapper<ItemIoDto>{

	@Override
	public ItemIoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ItemIoDto.builder()
				.itemIoNo(rs.getInt("item_io_no"))
				.itemIoTotal(rs.getInt("item_io_total"))
				.itemIoIn(rs.getInt("item_io_in"))
				.itemIoInTime(rs.getTimestamp("item_io_in_time"))
				.itemIoOut(rs.getInt("item_in_out"))
				.itemIoOutTIme(rs.getTimestamp("item_it_out_time"))
				.build();
					
	}
	
}
