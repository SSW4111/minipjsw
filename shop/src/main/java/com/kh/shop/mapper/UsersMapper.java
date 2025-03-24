package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.UsersDto;

@Component
public class UsersMapper implements RowMapper <UsersDto>{

	@Override
	public UsersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return UsersDto.builder()
				.usersEmail(rs.getString("users_email"))
				.usersPw(rs.getString("users_pw"))
				.usersContact(rs.getString("users_contact"))
				.usersLevel(rs.getString("users_level"))
				.usersNickname(rs.getString("users_nickname"))
				.usersDelivery((Integer)rs.getObject("users_delivery"))
				.build();
	}

}
