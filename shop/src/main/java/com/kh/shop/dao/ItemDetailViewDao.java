package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.mapper.ItemDetailViewMapper;

@Repository
public class ItemDetailViewDao {
//view 합칠까하다가... 시간나면 그때최적화...
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private ItemDetailViewMapper itemDetailViewMapper;
	
	//상세
	public ItemDetailViewDto selectOne(int itemNo) {
		String sql="select*from item_detail_view where item_no=? ";
		Object[] data = {itemNo};
		List<ItemDetailViewDto> list = jdbcTemplate.query(sql, itemDetailViewMapper,data);
		return list.isEmpty()? null : list.get(0);
	} 
	
	//컬러리스트뽑아오기
	public List<ItemDetailViewDto> selectColor(ItemDetailViewDto itemDetailViewDto) {
		String sql="select item_color from item where item_title=? "
				+ "and item_category=? and item_detail=? ";
		Object[] data = {itemDetailViewDto.getItemTitle(), itemDetailViewDto.getItemCategory(),
								itemDetailViewDto.getItemDetail()};
		List<ItemDetailViewDto> colorList = jdbcTemplate.query(sql, itemDetailViewMapper,data);
		return colorList.isEmpty()? null : colorList;
	}
	
	//사이즈 리스트뽑아오기 
	public List<ItemDetailViewDto> selectSize(ItemDetailViewDto itemDetailViewDto){
		String sql="select item_size from item where item_title=? "
				+ "and item_category=? and item_detail=? ";
		Object[] data = {itemDetailViewDto.getItemTitle(), itemDetailViewDto.getItemCategory(),
						itemDetailViewDto.getItemDetail()};
		List<ItemDetailViewDto> sizeList = jdbcTemplate.query(sql, itemDetailViewMapper,data);
		return sizeList.isEmpty()? null : sizeList;
	}
}
