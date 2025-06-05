package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.UsersOrderOptionsDto;
import com.kh.shop.mapper.UsersOrderOptionsMapper;

@Repository
public class UsersOrderOptionsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UsersOrderOptionsMapper mapper;
	
	public UsersOrderOptionsDto selectOne(String usersEmail) {
		String sql = "select * from users_order_options where users_email = ?";
		Object[] data = {usersEmail};
		List<UsersOrderOptionsDto> list = jdbcTemplate.query(sql, mapper, data);
		return list.isEmpty() ? null : list.getFirst();
	}
	public long sequence() {
		String sql = "select users_order_options_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, long.class); 
	}
	public boolean insert(UsersOrderOptionsDto dto) {
		String sql = "insert into users_order_options("
				+ "users_order_options_no, users_order_options_request, users_email"
				+ ") values(?,?,?)";
		Object[] data = {dto.getUsersOrderOptionsNo(), dto.getUsersOrderOptionsRequest(), dto.getUsersEmail()};
		return jdbcTemplate.update(sql,data) > 0;
	}
	public boolean delete(String usersEmail) {
		String sql = "delete users_order_options where users_email = ?";
		Object[] data = {usersEmail};
		return jdbcTemplate.update(sql,data) > 0;
	}
	public boolean delete(long usersOrderOptionsNo) {
		String sql = "delete users_order_options where users_order_options_no = ?";
		Object[] data = {usersOrderOptionsNo};
		return jdbcTemplate.update(sql,data) > 0;
	}
	public boolean update(UsersOrderOptionsDto dto) {
		String sql = "update users_order_options set users_order_options_request = ? "
				+ "where users_order_options_no = ?";
		Object[] data = {dto.getUsersOrderOptionsRequest(), dto.getUsersOrderOptionsNo()};
		return jdbcTemplate.update(sql,data) > 0;
	}
}
