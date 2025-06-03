package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.UsersOrderOptionsDto;

@Component
public class UsersOrderOptionsMapper implements RowMapper<UsersOrderOptionsDto>{

	@Override
	public UsersOrderOptionsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return UsersOrderOptionsDto.builder()
				.usersOrderOptionsNo(rs.getLong("users_order_options_no"))
				.usersOrderOptionsRequest(rs.getString("users_order_options_request"))
				.usersEmail(rs.getString("users_email"))
				
				.build();
	}
	
}
