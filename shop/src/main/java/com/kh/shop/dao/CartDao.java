package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.mapper.ItemDetailViewMapper;
import com.kh.shop.vo.MorePageVO;

@Repository
public class CartDao {

	@Autowired
	private ItemDetailViewMapper itemDetailViewMapper;
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
	
	//장바구니 이메일로조회 detailviewlist
	public List<ItemDetailViewDto>cartList(String usersEmail, MorePageVO morePageVO){
		String sql= "SELECT * FROM ( "
				+ "    SELECT rownum rn, TMP.* FROM ( "
				+ "        SELECT i.item_no, i.item_title, i.item_color, i.item_gender, i.item_category, i.item_detail,  "
				+ "               i.avestar, i.reviewscount, i.item_io_no, i.item_io_total, i.item_like, i.item_content, i.item_price "
				+ "        FROM ITEM_DETAIL_VIEW i "
				+ "        WHERE i.item_no IN (SELECT item_no FROM cart WHERE users_email = ?) "
				+ "    ) TMP "
				+ ") WHERE rn BETWEEN ? AND ? ";
		Object[] data = {usersEmail,morePageVO.getStartRownum(), morePageVO.getFinishRownum()};
		return jdbcTemplate.query(sql,itemDetailViewMapper, data);
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
