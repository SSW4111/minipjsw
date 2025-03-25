package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.UsersDto;
import com.kh.shop.mapper.UsersMapper;
import com.kh.shop.vo.PageVO;

@Repository
public class UsersDao {

	@Autowired
	private UsersMapper usersMapper;
	
	//가입수정삭제
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(UsersDto usersDto) {
		String sql= "insert into users( "
				+ "users_email, users_pw,users_contact, users_nickname)"
				+ " values(? ,? ,? ,?)";
		Object[] data = {usersDto.getUsersEmail(), usersDto.getUsersPw(), usersDto.getUsersContact(),
							usersDto.getUsersNickname()};
		
		jdbcTemplate.update(sql,data);
	}
	
	public void connect(int attachmentNo,String usersEmail) {
		String sql = "insert into users_profile(attachment_no,users_email)"
				+ " values(?,?)";
		Object[] data = {attachmentNo,usersEmail};
		jdbcTemplate.update(sql,data);
	}
	
	public boolean update(UsersDto usersDto) {
		String sql = "update users set users_contact=?, "
				+ "users_nickname= ?, where users_email=?";
		
		Object[] data = {usersDto.getUsersContact(),
				 usersDto.getUsersNickname(),usersDto.getUsersEmail()};
		
		return jdbcTemplate.update(sql,data) >0;
	}
	
	public boolean delete(String usersEmail) {
		String sql = "delete users where users_email=?";
		Object[] data = {usersEmail};
		
		return jdbcTemplate.update(sql,data)>0;
	}
	
	public UsersDto selectOne(String usersEmail) {
		String sql = "select*from users where users_email=?";
		Object[] data = {usersEmail};
		List<UsersDto> list = jdbcTemplate.query(sql, usersMapper, data);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public List<UsersDto> selectList(PageVO pageVO){
		if(pageVO.isList()) {
			String sql = "select * from( "
					+ " select rownum rn, TMP.*from("
					+ "select * from users order by users_email asc"
					+ ")TMP" 
					+ ")where rn between ? and ?";
			Object[] data  = {pageVO.getStartRownum(),pageVO.getFinishRownum()}; 
			return jdbcTemplate.query(sql,usersMapper,data);
		}
		else {
			String sql = "select * from("
					+ " select rownum rn, TMP.*from("
					+ "select * from users where instr(#1, ?) > 0"
					+ "order by #1 asc, users_email asc "
					+ ")TMP"
					+ ")where rn between ? and ?";
			sql = sql.replace("#1",pageVO.getColumn());
			Object[] data = {pageVO.getKeyword(), pageVO.getStartRownum(), pageVO.getFinishRownum()};
			return jdbcTemplate.query(sql, usersMapper,data);
		}
		}
	
	public int count(PageVO pageVO) {
		if(pageVO.isList()) {
		String sql = "select count(*) from users";
		return jdbcTemplate.queryForObject(sql, int.class);
		}
		else {
			String sql = "select count(*) from users where instr(#1, ?) >0";
			sql=sql.replace("#1", pageVO.getColumn());
			Object[] data= {pageVO.getKeyword()};
			return jdbcTemplate.queryForObject(sql,int.class,data);
		}
	}
}
