package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ReviewsDto;

@Repository
public class ReviewsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	//시쿠너스
	public int sequence() {
		String sql="select reviews_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	public void insert(ReviewsDto reviewsDto) {
		String sql = "insert into reviews(reviews_no,reviews_title,reviews_content, reviews_star) "
				+ "values(?, ?, ?, ?)";
		Object[] data = {reviewsDto.getReviewsNo(),reviewsDto.getReviewsTitle(),reviewsDto.getReviewsContent(),reviewsDto.getReviewsStar()};
		jdbcTemplate.update(sql,data);
	}
	
	public boolean delete(int reviewsNo) {
		String sql = "delete from reviews where reviews_no=?";
		Object[] data = {reviewsNo};
		return jdbcTemplate.update(sql, data)>0;
	}
	
	public boolean update(ReviewsDto reviewsDto) {
		String sql = "update reviews set reviews_title=?, reviews_content=?, reviews_star=?"
						+ "where reviews_no=?";
		Object[] data = {reviewsDto.getReviewsTitle(), reviewsDto.getReviewsContent(),
				reviewsDto.getReviewsStar(),reviewsDto.getReviewsNo()};
		return jdbcTemplate.update(sql,data)>0;
	}
	
	//리스트잠시 패스
	
	
}
