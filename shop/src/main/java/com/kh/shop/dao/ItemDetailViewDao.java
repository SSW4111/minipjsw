
package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.dto.ItemIoDto;
import com.kh.shop.mapper.ItemDetailViewMapper;
import com.kh.shop.mapper.ItemDetailViewMapper.ItemColorMapper;
//import com.kh.shop.mapper.ItemDetailViewMapper.ItemColorMapper;
import com.kh.shop.mapper.ItemDetailViewMapper.ItemSizeMapper;
import com.kh.shop.mapper.ItemIoMapper;


@Repository
public class ItemDetailViewDao {
//view 합칠까하다가... 시간나면 그때최적화...
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private ItemDetailViewMapper itemDetailViewMapper;
	
	@Autowired
	private ItemColorMapper itemColorMapper;
	
	@Autowired
	private ItemSizeMapper itemSizeMapper;
	
	@Autowired
	private ItemIoMapper itemIoMapper;

	//상세
	public ItemDetailViewDto selectOne(int itemNo) {
		String sql="select * from item_detail_view where item_no=? ";
		Object[] data = {itemNo};
		List<ItemDetailViewDto> list = jdbcTemplate.query(sql, itemDetailViewMapper,data);
		return list.isEmpty()? null : list.get(0);
	} 
//	
	//컬러리스트뽑아오기
	public List<ItemDetailViewDto> selectColor(ItemDetailViewDto itemDetailViewDto) {
		String sql="select item_color from item where item_title=? and item_category=? and item_detail=? ";
		Object[] data = {itemDetailViewDto.getItemTitle(), itemDetailViewDto.getItemCategory(),
								itemDetailViewDto.getItemDetail()};
		List<ItemDetailViewDto> colorList = jdbcTemplate.query(sql, itemColorMapper,data);
		return colorList.isEmpty()? null : colorList;
	}

	
//	//사이즈 리스트뽑아오기  size_name으로 item_in에서 무슨번호로? //디테일에쓸검ㅁㅊ
	public List<ItemDetailViewDto> selectSize(int itemIoNo){
		String sql="select size_name from item_io where item_io_no=? ";
		Object[] data = {itemIoNo};
		List<ItemDetailViewDto> sizeList = jdbcTemplate.query(sql, itemSizeMapper,data);
		return sizeList.isEmpty()? null : sizeList;
	}
	
	//dto 리스트가필요한데
	public List<ItemIoDto> selectIoList(int itemNo){
		String sql="select * from item_io where item_io_no= ?";
		Object[] data = {itemNo};
		List<ItemIoDto> ioList = jdbcTemplate.query(sql, itemIoMapper,data);
		return ioList.isEmpty()? null : ioList;
		 
	}

}
 
