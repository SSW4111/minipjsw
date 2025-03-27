package com.kh.shop.dao;

import java.util.ArrayList;
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
		String sql="delete from item where item_no=?";
		Object[] data = {itemNo};
		return jdbcTemplate.update(sql,data)>0;
	}
	//수정
	public boolean update(ItemDto itemDto) {
		String sql = "update item set item_color=?, item_size=?, item_title=?, item_content=?, "
				+ "item_gender=?, item_category=?, item_detail=? where item_no=?";
		Object[] data = {itemDto.getItemColor(), itemDto.getItemSize(), itemDto.getItemTitle(),
				itemDto.getItemContent(), itemDto.getItemGender(),itemDto.getItemCategory(),itemDto.getItemDetail(),
				itemDto.getItemNo()};
		return jdbcTemplate.update(sql,data) >0;
	}
	//상세 
	public ItemDto selectOne(int itemNo) {
		String sql="select*from item where item_no=?";
		Object[] data = {itemNo};
		List<ItemDto> list = jdbcTemplate.query(sql,itemMapper,data);
		return list.isEmpty()? null : list.get(0);
	}
	
	
	//카운트 , color size 체크시 목록계산추가
	public int count(ItemVO itemVO) {
		StringBuilder sql = new StringBuilder();
		if(itemVO.isList()) {
			sql.append("select count(*) from item");
			return jdbcTemplate.queryForObject(sql.toString(), int.class);
		}
		else {
			//모든타입넣는리스트생성
			List<Object> dataList = new ArrayList <>();
			 sql.append("select count(*) from item ").append("where 1=1 "); 
			//전체검색 이게뭐임ㅠㅠ디비막쓴다
			if(itemVO.isSearch()) {
				 sql.append(" and ( ")
	                .append("instr(item_color, ?) > 0 or ")
	                .append("instr(item_size, ?) > 0 or ")
	                .append("instr(item_title, ?) > 0 or ")
	                .append("instr(item_content, ?) > 0 or ")
	                .append("instr(item_gender, ?) > 0 or ")
	                .append("instr(item_category, ?) > 0 or ")
	                .append("instr(item_detail, ?) > 0)");
				
			String keyword = itemVO.getKeyword();
			for (int i=0; i<7 ; i++) {
				dataList.add(keyword);
			}
			}
			//컬러골랐다면
			if(itemVO.colorCheck()) {
				sql.append(" and item_color = ? ");
				dataList.add(itemVO.getColor());
			}
			if(itemVO.sizeCheck()) {
				sql.append(" and item_size= ? ");
				dataList.add(itemVO.getClothesSize());
			}
		return jdbcTemplate.queryForObject(sql.toString(), int.class,dataList.toArray());
			
		}
		}
	
	
//	//리스트
//public List<itemDto>selctList(ItemVO itemVO){
//	
//	select * from item inner join item_images 
//	on item.item_no = item_images.item_no
//	inner join attachment 
//	on item_images.attachment_no = attachment.attachment_no
//	where item_images.item_no=?
//}
	}
	

