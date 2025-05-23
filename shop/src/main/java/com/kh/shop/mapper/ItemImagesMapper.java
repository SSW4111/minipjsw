package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemImagesMapper implements RowMapper<Integer>{

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		 return rs.getInt("attachment_no");
	}

	
}
