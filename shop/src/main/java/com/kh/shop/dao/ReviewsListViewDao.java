package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ReviewsListViewDto;
import com.kh.shop.mapper.ReviewsListViewMapper;
import com.kh.shop.vo.ItemVO;

@Repository
public class ReviewsListViewDao {
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ReviewsListViewMapper reviewsListViewMapper;
	
	

	public List<ReviewsListViewDto>selectList(int itemNo, ItemVO itemVO){
		String sql = "select * from ( "
				+ " select rownum rn, TMP.* from( "
				+ " select * from reviews_list_view where item_no=? "
				+ " order by reviews_no desc "
				+ " )TMP "
				+ " ) where rn between ? and ?";
		Object[] data = {itemNo, itemVO.getStartRownum(), itemVO.getFinishRownum()};
		return jdbcTemplate.query(sql, reviewsListViewMapper,data);
	}
}
