package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.kh.shop.aop.UeresLoginInterceptor;
import com.kh.shop.dto.AttachmentDto;
import com.kh.shop.mapper.AttachmentMapper;

@Repository
public class AttachmentDao {

    private final UeresLoginInterceptor ueresLoginInterceptor;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AttachmentMapper attachmentMapper;

    AttachmentDao(UeresLoginInterceptor ueresLoginInterceptor) {
        this.ueresLoginInterceptor = ueresLoginInterceptor;
    }
	
	public int sequence() {
		String sql ="select attachment_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	
	public void insert(AttachmentDto attachmentDto) {
	String sql = "insert into attachment( "
			+ " attachment_no, attachment_name, attachment_type, attachment_size) "
			+ "values(?, ?, ? ,?)";
	Object[] data = {attachmentDto.getAttachmentNo(), attachmentDto.getAttachmentName(),
			attachmentDto.getAttachmentType(), attachmentDto.getAttachmentSize()};
	
	jdbcTemplate.update(sql,data);
}
	
	public boolean delete(int attachmentNo) {
		System.out.println(attachmentNo);
		String sql= "delete attachment where attachment_no=?";
		Object[] data = {attachmentNo};
		return jdbcTemplate.update(sql, data) >0;
	}
	
	public AttachmentDto selectOne(int attachmentNo) {
		String sql="select * from attachment where attachment_no=?";
		Object data[] = {attachmentNo};
		List<AttachmentDto> list = jdbcTemplate.query(sql, attachmentMapper,data);
		return list.isEmpty()? null : list.get(0);
	}
}