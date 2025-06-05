package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.shop.dto.DeliveryDto;
import com.kh.shop.mapper.DeliveryMapper;

@Repository
public class DeliveryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DeliveryMapper deliveryMapper;
	
	//등록/리스트/수정/삭제있음 
	public int sequence() {
		String sql="select delivery_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public void insert(DeliveryDto deliveryDto) {
		String sql = "insert into delivery( delivery_no, users_email, delivery_post, "
				+ "delivery_address1, delivery_address2, delivery_type)"
				+ "values(?, ?, ?, ?, ?, ?)";
		Object[] data = {deliveryDto.getDeliveryNo(),deliveryDto.getUsersEmail() ,deliveryDto.getDeliveryPost(),
				deliveryDto.getDeliveryAddress1(),deliveryDto.getDeliveryAddress2(),
				deliveryDto.getDeliveryType()};
		jdbcTemplate.update(sql,data);
	}
	
	public boolean delete(int deliveryNo) {
		String sql ="delete from delivery where delivery_no=?";
		Object[]data = {deliveryNo};
		return jdbcTemplate.update(sql,data)>0;
	}
	
	public List<DeliveryDto> selectUserDelivery(String usersEmail){
		String sql = " select * from delivery where users_email =?";
		Object[] data = {usersEmail};
		return jdbcTemplate.query(sql, deliveryMapper, data);
	}

	public int count(String usersEmail) {
		String sql ="select count(*) from delivery where users_email=?";
		Object[] data = {usersEmail};
		return jdbcTemplate.queryForObject(sql, int.class,data);
	}
	//주소한개의 정보
	public DeliveryDto selectOne(String usersEmail) {
		String sql="select * from delivery where users_email = ? "
				+ "and delivery_main = 'Y'";
		Object[] data = {usersEmail};
		List<DeliveryDto> list = jdbcTemplate.query(sql,deliveryMapper,data);
		return list.isEmpty()? null : list.get(0);
	} 
	//수정
	public boolean update(DeliveryDto deliveryDto) {
		String sql = "update delivery set delivery_post=?, delivery_address1=?, "
				+ " delivery_address2=?, delivery_type=? "
				+ " where delivery_no=? ";
		Object[] data = {deliveryDto.getDeliveryPost(), deliveryDto.getDeliveryAddress1(),
				deliveryDto.getDeliveryAddress2(), deliveryDto.getDeliveryType(), deliveryDto.getDeliveryNo()};
		return jdbcTemplate.update(sql, data)>0;
	}

	@Transactional
	public boolean setMain(String usersEmail, long deliveryNo) {
		String sql1 = "update delivery set delivery_main = 'N' where delivery_main = 'Y' and users_email = ?";
		Object[] data1 = {usersEmail};
		jdbcTemplate.update(sql1,data1);
		
		String sql2 = "update delivery set delivery_main = 'Y' where delivery_no = ? and users_email = ? ";
		Object[] data2 = {deliveryNo, usersEmail};
		return jdbcTemplate.update(sql2, data2) > 0;
	}

}














