package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemIoDto;
import com.kh.shop.mapper.ItemIoMapper;
import com.kh.shop.vo.MorePageVO;

@Repository
public class ItemIoDao {

	@Autowired
	private ItemIoMapper itemIoMapper;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//인서트추가
	public void insert(ItemIoDto itemIoDto) {
		String sql = "insert into item_io(item_io_in, size_name) values(?, ?)";
		Object[] data = {itemIoDto.getItemIoIn(), itemIoDto.getSizeName()};
		jdbcTemplate.update(sql,data);
	}
	//수정 (수정시각변경)
	public boolean update(ItemIoDto itemIoDto) {
		String sql = "update item_io set item_io_in = ?,item_io_in_time =systimestamp ,item_io_out = ?, "
				+ "	size_name = ? where item_io_no = ?";
		Object[] data = {itemIoDto.getItemIoIn(),itemIoDto.getItemIoOut(),itemIoDto.getSizeName(),itemIoDto.getItemIoNo()};
		return jdbcTemplate.update(sql,data)>0;
	}
	//item지울때 얘도 같이지워버림 ((((itemNo로받음)))) itemiono == itemno
	public boolean delete(int itemNo) {
		String sql = "delete from item_io where item_io_no = ?";
		Object[] data = {itemNo};
		return jdbcTemplate.update(sql, data) > 0;
	}
	
	//리스트 뽑기 //itemNo로받음 
	public List<ItemIoDto> selectList(int itemNo,MorePageVO morePageVO){
		String sql = "select * from(  "
				+ "select rownum rn, TMP.* from( "
				+ "select * from item_io where item_io_no= ? ORDER BY item_io_no asc"
				+ ")  "
				+ "TMP) "
				+ "where rn between ? and ?; ";
		Object[] data = {itemNo, morePageVO.getStartRownum(), morePageVO.getFinishRownum()};
		return jdbcTemplate.query(sql,itemIoMapper ,data);
	}
}
