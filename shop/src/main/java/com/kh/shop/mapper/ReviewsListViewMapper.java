package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ReviewsListViewDto;

@Component
public class ReviewsListViewMapper implements RowMapper<ReviewsListViewDto>{

	@Override
	public ReviewsListViewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ReviewsListViewDto.builder()
				.reviewsNo(rs.getInt("reviews_no"))
				.reviewsTitle(rs.getString("reviews_title"))
				.reviewsContent(rs.getString("reviews_content"))
				.reviewsStar(rs.getInt("reviews_star"))
				.reviewsWtime(rs.getTimestamp("reviews_wtime"))
				.itemNo(rs.getInt("item_no"))
				.itemTitle(rs.getString("item_title"))
				.itemGender(rs.getString("item_gender"))
				.itemCategory(rs.getString("item_category"))
				.itemDetail(rs.getString("item_detail"))
				.usersEmail(rs.getString("users_email"))
				.usersLevel(rs.getString("users_level"))
				.usersNickname(rs.getString("usersNickname"))
				.build();
				
	}

}
