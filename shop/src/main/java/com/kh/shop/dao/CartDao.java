package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.mapper.ItemDetailViewMapper.CartMapper;
import com.kh.shop.vo.MorePageVO;

@Repository
public class CartDao {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	//장바구니 넣기((좋아요)) //장바구니 버튼 클릭시 들어가는것만됨 삭제는 리스트에서만
	public void cartInsert(int itemNo, String usersEmail) {
		String sql = "insert into cart(item_no, users_email) values(?, ?)";
		Object[] data = {itemNo, usersEmail};
		jdbcTemplate.update(sql,data);
	}
	//이미 장바구니에 가지고있는지 확인하기
	public boolean cartCheck(String usersEmail, int itemNo) {
		String sql= "select count(*) from cart where users_email= ? and item_no =?";
		Object[] data = {usersEmail, itemNo};
		return jdbcTemplate.queryForObject(sql, int.class,data) >0;
	}
	
	//장바구니 no리스트조회
	public List<ItemDetailViewDto> myCartList(String usersEmail, MorePageVO morePageVO){
		String sql= "select * from( "
				+ "   select rownum rn, TMP.* from( "
				+ "         select * from cart where users_email = ?  "
				+ "	order by item_no asc "
				+ "   )TMP "
				+ ") where rn between ? and ? "
				+ "";
		Object[] data = {usersEmail, morePageVO.getStartRownum(), morePageVO.getFinishRownum()};
		return jdbcTemplate.query(sql, cartMapper,data);
	}
	
	//장바구니 아이템 삭제((좋아요해제))
	public boolean deleteCart(int itemNo, String usersEmail) {
		String sql = "delete from cart where item_no=? and users_email=?";
		Object[] data = {itemNo, usersEmail};
		return jdbcTemplate.update(sql,data)>0;
		}
	
	//개수
	public int countCart(String usersEmail) {
		String sql = "select count(*) from cart where users_email=?";
		Object[] data = {usersEmail};
		return jdbcTemplate.queryForObject(sql, int.class,data);
	}
}
