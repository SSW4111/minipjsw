package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.IoListViewDto;

@Component
public class IoListViewMapper implements RowMapper<IoListViewDto>{

	@Override
	public IoListViewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return IoListViewDto.builder()
				.itemIoLogEvent(rs.getString("item_io_log_event"))
				.itemIoLogTime(rs.getTimestamp("item_io_log_time"))
				.itemIoLogNumber(rs.getInt("item_io_log_number"))
				.sizeName(rs.getString("size_name"))
				.itemNo(rs.getInt("item_no"))
				.build();
					
	}
	
}
