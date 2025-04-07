package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.DeliveryDto;

@Component
public class DeliveryMapper implements RowMapper <DeliveryDto>{

	@Override
	public DeliveryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return DeliveryDto.builder()
				.deliveryNo(rs.getInt("delivery_no"))
				.usersEmail(rs.getString("users_email"))
				.deliveryPost(rs.getString("delivery_post"))
				.deliveryAddress1(rs.getString("delivery_address1"))
				.deliveryAddress2(rs.getString("delivery_address2"))
				.deliveryType(rs.getString("delivery_type"))
				.build();
	}

}
