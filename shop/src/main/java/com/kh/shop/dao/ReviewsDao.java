package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ReviewsDto;
import com.kh.shop.mapper.ReviewsMapper;
import com.kh.shop.vo.ItemVO;

@Repository
public class ReviewsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ReviewsMapper reviewsMapper;
	//시쿠너스
	public int sequence() {
		String sql="select reviews_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	//카운트
	public int count(int itemNo) {
		String sql = "select count(*) from reviews where item_no=?";
		Object[] data = {itemNo};
		return jdbcTemplate.queryForObject(sql, int.class,data);
	}
	

	public void insert(ReviewsDto reviewsDto) {
		String sql = "insert into reviews(reviews_no,reviews_title,reviews_content, reviews_star, reviews_wtime, item_no) "
				+ "values(?, ?, ?, ?, SYSTIMESTAMP,?)";
		Object[] data = {reviewsDto.getReviewsNo(),reviewsDto.getReviewsTitle(),reviewsDto.getReviewsContent(),
				 			reviewsDto.getReviewsStar(), reviewsDto.getItemNo()};
		jdbcTemplate.update(sql,data);
	}
	
	public boolean delete(int reviewsNo) {
		String sql = "delete from reviews where reviews_no=?";
		Object[] data = {reviewsNo};
		return jdbcTemplate.update(sql, data)>0;
	}
	
	public boolean update(ReviewsDto reviewsDto) {
		String sql = "update reviews set reviews_title=?, reviews_content=?, reviews_star=?"
						+ " where reviews_no=?";
		Object[] data = {reviewsDto.getReviewsTitle(), reviewsDto.getReviewsContent(),
				reviewsDto.getReviewsStar(),reviewsDto.getReviewsNo()};
		return jdbcTemplate.update(sql,data)>0;
	}
	
	//리스트
	
	public List<ReviewsDto>selectList(ItemVO itemVO){
		String sql ="select * from( "
				+ "select rownum rn, "
				+ "TMP.* from ( "
				+ "select * from reviews order by reviews_no desc ) "
				+ " TMP ) "
				+ " where rn between ? and ?";
		Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
		return jdbcTemplate.query(sql, reviewsMapper,data);
	}
	
	//찾는용
	public ReviewsDto selectOne(int reviewsNo) {
		String sql = "select * from reviews where reviews_no=? ";
		Object[] data = {reviewsNo};
		List<ReviewsDto>list = jdbcTemplate.query(sql, reviewsMapper,data);
		return list.isEmpty() ? null : list.get(0);
	}
	//파일커넥
	public void connect(int reviewsNo, int attachmentNo) {
		String sql = "insert into reviews_images( "
				+ "reviews_no, attachment_no )"
				+ "values(?,?)";
		Object[] data = {reviewsNo, attachmentNo};
		jdbcTemplate.update(sql,data);
	}
	
	//리뷰사진찾기여러장
	public List<Integer> findAttachments(int reviewsNo){
		String sql = "select attachment.attachment_no from reviews "
				+ "left outer join reviews_images on reviews.reviews_no = reviews_images.reviews_no "
				+ "left outer join attachment on reviews_images.attachment_no = attachment.attachment_no "
				+ "where reviews.reviews_no =? "
				+ "order by attachment_no asc";
		
		return jdbcTemplate.queryForList(sql ,Integer.class ,reviewsNo);
	}
	
}
