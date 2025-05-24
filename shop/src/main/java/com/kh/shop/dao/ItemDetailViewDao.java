
package com.kh.shop.dao;

import java.util.ArrayList;
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
		String sql="select item_color, item_no from item where item_title=? and item_category=? and item_detail=? ";
		Object[] data = {itemDetailViewDto.getItemTitle(), itemDetailViewDto.getItemCategory(),
								itemDetailViewDto.getItemDetail()};
		List<ItemDetailViewDto> colorList = jdbcTemplate.query(sql, itemColorMapper,data);
		return colorList.isEmpty()? null : colorList;
	}


	public int selectColorNo(int itemNo) {
	    String sql = 
	        "SELECT attachment_no " +
	        "FROM ( " +
	        "    SELECT attachment_no " +
	        "    FROM item_images " +
	        "    WHERE item_no = ? " +
	        "    ORDER BY attachment_no ASC " +
	        ") " +
	        "WHERE ROWNUM = 1";

	    Object[] data = { itemNo };
	    
	    return jdbcTemplate.queryForObject(sql, int.class, data);
	}
	
//	//사이즈 리스트뽑아오기  size_name으로 item_in에서 무슨번호로? //디테일에쓸검ㅁㅊ
	public List<ItemDetailViewDto> selectSize(int itemIoNo){
		String sql="select size_name from item_io where item_io_no=? ";
		Object[] data = {itemIoNo};
		List<ItemDetailViewDto> sizeList = jdbcTemplate.query(sql, itemSizeMapper,data);
		return sizeList.isEmpty()? null : sizeList;
	}
	


	public Integer findAttachment(int itemNo) {
		String sql = "select attachment_no from item_images "
				+ "where item_no = ?";
		Object[]data = {itemNo};
//		System.out.println("attachment No : " + jdbcTemplate.queryForObject(sql, int.class,data));
		return jdbcTemplate.queryForObject(sql, int.class,data);
	}
	
	
	public void connect(int attachmentNo,int itemNo) {
		String sql = "insert into item_images(attachment_no, users_email)"
				+ " values(?,?)";
		Object[] data = {attachmentNo,itemNo};
		jdbcTemplate.update(sql,data);
	}

	//dto 리스트가필요한데
	public List<ItemIoDto> selectIoList(int itemNo){
		String sql="select * from item_io where item_no= ?";
		Object[] data = {itemNo};
		List<ItemIoDto> ioList = jdbcTemplate.query(sql, itemIoMapper,data);
		return ioList.isEmpty()? null : ioList;
		 
	}

	//---결제 요청전 아이템정보---
	//item no list받아서 하나하나 매핑해서 저장하기...
	public List<ItemDetailViewDto> selectList(List<Integer>itemNoList){
		List<ItemDetailViewDto>itemList = new ArrayList<>();
		for(int item : itemNoList) {
			ItemDetailViewDto itemDetail =selectOne(item);
			itemList.add(itemDetail);
		}
		return itemList;
	}
}










 
