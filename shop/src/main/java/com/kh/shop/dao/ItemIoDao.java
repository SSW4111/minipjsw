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
	
	public int sequence() {
		String sql="select item_io_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	
	public boolean insert(ItemIoDto itemIoDto) {
		String sql = "insert into item_io( item_no, size_name, item_io_no, item_io_total) values(?,?,?,?)";
		Object[] data = { itemIoDto.getItemNo(), itemIoDto.getSizeName(), itemIoDto.getItemIoNo(), itemIoDto.getItemIoTotal()};
		return jdbcTemplate.update(sql,data) > 0;
	}
	
	public int getTotal(ItemIoDto itemIoDto) {
		String sql = "select item_io_total from item_io where item_no = ? and size_name = ?";
		Object[] data = {itemIoDto.getItemNo(), itemIoDto.getSizeName()};
		return jdbcTemplate.update(sql,data);
	}
	

	
	//수정 (수정시각변경)
	public boolean update(ItemIoDto itemIoDto) {
		String sql = "update item_io set item_io_total = ?,  "
				+ "	size_name = ? where item_io_no = ?";
		Object[] data = {itemIoDto.getItemIoTotal(),itemIoDto.getSizeName(),itemIoDto.getItemIoNo()};
		return jdbcTemplate.update(sql,data)>0;
	}
	
	//item지울때 전부삭제
	public boolean delete(int itemNo) {
		String sql = "delete from item_io where item_no = ?";
		Object[] data = {itemNo};
		return jdbcTemplate.update(sql, data) > 0;
	}
	//io하나만삭제
	public boolean deleteOne(String sizeName, int itemNo) {
		String sql=" delete from item_io where size_name = ? and item_no =?";
		Object[] data = {sizeName, itemNo};
		return jdbcTemplate.update(sql,data)>0;
	}
	
	//리스트 뽑기 //itemNo로받음 
	public List<ItemIoDto> selectList(int itemNo,MorePageVO morePageVO){
		String sql = "select * from(  "
				+ "select rownum rn, TMP.* from( "
				+ "select * from item_io where item_no= ? ORDER BY item_no asc"
				+ ")  "
				+ "TMP) "
				+ "where rn between ? and ? ";
		Object[] data = {itemNo, morePageVO.getStartRownum(), morePageVO.getFinishRownum()};
		return jdbcTemplate.query(sql,itemIoMapper ,data);
	}
	
	//셀렉원
	public ItemIoDto selectOne(int itemIoNo) {
		String sql = "select * from item_io where item_io_no= ?";
		Object[] data = {itemIoNo};
		List<ItemIoDto> list = jdbcTemplate.query(sql, itemIoMapper, data);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public ItemIoDto selectOne2(int itemIoNo, String sizeName) {
		String sql = "select * from item_io where item_io_no= ? and size_name =? ";
		Object[] data = {itemIoNo, sizeName};
		List<ItemIoDto> list = jdbcTemplate.query(sql, itemIoMapper, data);
		return list.isEmpty() ? null : list.get(0);
	}
}
