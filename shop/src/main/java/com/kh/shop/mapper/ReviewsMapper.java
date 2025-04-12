package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ReviewsDto;

@Component
public class ReviewsMapper implements RowMapper <ReviewsDto>{

	@Override
	public ReviewsDto mapRow(ResultSet rs, int rowNum) throws SQLException {		
		return ReviewsDto.builder()
				.reviewsNo(rs.getInt("reviews_no"))
				.reviewsTitle(rs.getString("reviews_title"))
				.reviewsContent(rs.getString("reviews_content"))
				.reviewsStar((Integer)rs.getObject("reviews_star"))
				.reviewsWtime(rs.getTimestamp("reviews_wtime"))
				.usersNickname(rs.getString("users_nickname"))
				.itemNo(rs.getInt("item_no"))
				.usersEmail(rs.getString("users_eamil"))
				.build();
	}

}