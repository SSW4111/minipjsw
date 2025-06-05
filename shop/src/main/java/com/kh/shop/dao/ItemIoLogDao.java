package com.kh.shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemIoLogDto;
import com.kh.shop.mapper.ItemIoLogMapper;

@Repository
public class ItemIoLogDao {
	@Autowired
	private ItemIoLogMapper itemIoLogMapper;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public int sequence() {
		String sql="select item_io_log_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	public boolean insert(ItemIoLogDto itemIoLogDto) {
		String sql = "insert into item_io_log(item_io_log_no, item_io_log_event, item_io_log_number, item_io_no)"
				+ "	values(?,?,?,?)";
		Object[]data = {itemIoLogDto.getItemIoLogNo(), itemIoLogDto.getItemIoLogEvent(),
							itemIoLogDto.getItemIoLogNumber(),itemIoLogDto.getItemIoNo()};
		return jdbcTemplate.update(sql,data) >0;
	}
}
