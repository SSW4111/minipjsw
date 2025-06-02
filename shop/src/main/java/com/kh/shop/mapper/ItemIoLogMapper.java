package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemIoDto;
import com.kh.shop.dto.ItemIoLogDto;

@Component
public class ItemIoLogMapper implements RowMapper<ItemIoLogDto>{

	@Override
	public ItemIoLogDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ItemIoLogDto.builder()
				.itemIoLogNo(rs.getLong("item_io_log_no"))
				.itemIoLogEvent(rs.getString("item_io_log_event"))
				.itemIoLogTime(rs.getTimestamp("item_io_log_time"))
				.itemIoNo(rs.getInt("item_io_no"))
				.itemIoLogNumber(rs.getInt("item_io_log_number"))
				.build();
					
	}
	
}
