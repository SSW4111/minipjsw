package com.kh.shop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemDetailViewDto;

@Component
public class ItemDetailViewMapper implements RowMapper<ItemDetailViewDto>{
	@Override
	public ItemDetailViewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ItemDetailViewDto.builder()
				.itemNo(rs.getInt("item_no"))
				.itemColor(rs.getString("item_color"))
				.itemTitle(rs.getString("item_title"))
				.itemContent(rs.getString("item_content"))
				.itemGender(rs.getString("item_gender"))
				.itemCategory(rs.getString("item_category"))
				.itemDetail(rs.getString("item_detail"))
				.itemAveStar(rs.getFloat("avestar"))
				.itemReviewsCount(rs.getInt("reviewscount"))
				.itemIoNo(rs.getInt("item_io_no"))
				.itemIoTotal(rs.getInt("item_io_total"))
				.itemLike(rs.getInt("item_like"))
				.sizeName(rs.getString("size_name"))
				.build();
	}
		
@Component
public class ItemColorMapper implements RowMapper<ItemDetailViewDto>{

@Override
public ItemDetailViewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	return ItemDetailViewDto.builder()
			.itemNo(rs.getInt("item_no"))
			.itemColor(rs.getString("item_color"))
			.build();
	}
	}

@Component
public class ItemSizeMapper implements RowMapper<ItemDetailViewDto>{

	@Override
	public ItemDetailViewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		return ItemDetailViewDto.builder()
				.sizeName(rs.getString("size_name"))
				.build();
	}
	
}
}
