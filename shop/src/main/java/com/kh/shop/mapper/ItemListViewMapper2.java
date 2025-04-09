package com.kh.shop.mapper;


import java.math.BigDecimal;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.shop.dto.ItemListViewDto2;

@Component
public class ItemListViewMapper2 implements RowMapper<ItemListViewDto2> {

	@Override
	public ItemListViewDto2 mapRow(ResultSet rs, int rowNum) throws SQLException {
		Array itemNoArray = rs.getArray("item_no_list");
		List<Integer> itemNoList = null;
		if (itemNoArray != null) {
		    BigDecimal[] itemNoBigDecimalArray = (BigDecimal[]) itemNoArray.getArray();
		    itemNoList = Arrays.stream(itemNoBigDecimalArray)
		                       .map(BigDecimal::intValue)  
		                       .collect(Collectors.toList()); 
		}

        
		return ItemListViewDto2.builder()
				.itemTitle(rs.getString("item_title"))
//				.itemNo(rs.getInt("item_no"))
//				.itemGender(rs.getString("item_gender"))
				.itemCategory(rs.getString("item_category"))
				.itemDetail(rs.getString("item_detail"))
				.itemNoList(itemNoList)
				.build();
	}

}