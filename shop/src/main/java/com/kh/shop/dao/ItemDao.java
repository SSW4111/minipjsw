package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemDto;
import com.kh.shop.mapper.ItemMapper;
import com.kh.shop.vo.ItemVO;

@Repository
public class ItemDao {

	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//시퀀스
	public int sequence() {
		String sql="select item_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	//등록
	public void insert(ItemDto itemDto) {
		String sql="insert into item(item_no, item_color, item_size, item_title, item_content,"
				+ " item_gender, item_category, item_detail) "
				+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] data = {itemDto.getItemNo(), itemDto.getItemColor(), itemDto.getItemSize(),
				 itemDto.getItemTitle(), itemDto.getItemContent(), itemDto.getItemGender(),
				  itemDto.getItemCategory(),itemDto.getItemDetail()};
			jdbcTemplate.update(sql,data);
	}
	//삭제
	public boolean delete(int itemNo) {
		String sql="delete item where item_no=?";
		Object[] data = {itemNo};
		return jdbcTemplate.update(sql,data)>0;
	}
	//수정
	public boolean update(ItemDto itemDto) {
		String sql = "update item set item_color=?, item_size=?, item_title=?, item_content=?, "
				+ "item_gender=?, item_category=?, item_detail=?";
		Object[] data = {itemDto.getItemColor(), itemDto.getItemSize(), itemDto.getItemTitle(),
				itemDto.getItemContent(), itemDto.getItemGender(),itemDto.getItemCategory(),itemDto.getItemDetail()};
		return jdbcTemplate.update(sql,data) >0;
	}
	//상세
	public ItemDto selectOne(int itemNo) {
		String sql="select*from item where item_no=?";
		Object[] data = {itemNo};
		List<ItemDto> list = jdbcTemplate.query(sql,itemMapper,data);
		return list.isEmpty()? null : list.get(0);
	}
	//카운트
	public int count(ItemVO itemVO) {
		if(itemVO.isList()) {
			String sql = "select count(*) from item";
			return jdbcTemplate.queryForObject(sql, int.class);
		}
		else {
			String sql= "select count(*) from item where instr(#1, ?)>0";
			sql = sql.replace("#1", itemVO.getColumn());
			Object[] data = {itemVO.getKeyword()};
			return jdbcTemplate.queryForObject(sql, int.class,data);
		}
		
	}
	
	//리스트 아이거 리스트뽑으려면  메인이미지 추가해서 해야되는거 아닌감
//	public List<ItemDto> selectList(ItemVO itemVO){
//		String sql = "select * from( "
//				+ "select rownum rn, TMP.* from("
//				+ "select * from item order by item_no desc"
//				+ ")TMP"
//				+ ")where rn between ? and ? ";
//	}
}
