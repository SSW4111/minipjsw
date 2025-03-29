package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.CertDto;
import com.kh.shop.mapper.CertMapper;

@Repository
public class CertDao {
	@Autowired
	private CertMapper certMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(CertDto certDto) {
		String sql = "insert into cert(cert_email, cert_number) values(?,?)";
		Object[] data = {certDto.getCertEmail(),certDto.getCertNumber()};
		jdbcTemplate.update(sql, data);
	}
	
	public void update(CertDto certDto) {
		String sql = "update cert set cert_number=?, cert_time = systimestamp where cert_email = ?";
		Object[] data = {certDto.getCertNumber(), certDto.getCertEmail()};
		jdbcTemplate.update(sql, data);
	}
	
	public CertDto selectOne(String certEmail) {
		String sql = "select * from cert where cert_email = ?";
		Object[] data = {certEmail};
		List<CertDto> list = jdbcTemplate.query(sql, certMapper, data);
		return list.isEmpty() ? null:list.get(0) ;
	}
	
	public boolean confirm(String certEmail) {
		String sql = "update cert set cert_confirm = systimestamp where cert_email=?";
		Object[] data = {certEmail};
		return jdbcTemplate.update(sql, data) > 0;
	}
	
	public void delete(String certEmail) {
		String sql = "delete cert where cert_email=?";
		Object[] data = {certEmail};
		jdbcTemplate.update(sql, data);
	}
	
	public void insertOrUpdate(CertDto certDto) {
		if(selectOne(certDto.getCertEmail()) == null) {
			insert(certDto);
		}
		else {
			update(certDto);
		}
	}
	
}
















































