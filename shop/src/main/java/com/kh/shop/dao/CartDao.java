package com.kh.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.CartDto;
import com.kh.shop.mapper.CartJoinMapper;
import com.kh.shop.mapper.CartMapper;
import com.kh.shop.vo.CartJoinVO;
import com.kh.shop.vo.PageVO;


@Repository
public class CartDao {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CartJoinMapper cartJoinMapper;
	//시퀀스
	public int sequence() {
		String sql="select cart_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	//장바구니에 없을경우 그냥추가 io번호다를경우 그냥추가 둘다아닐시 qty만추가
	public void addOrUpdateCart(CartDto cartDto) {
	    CartDto findCart = findCart(cartDto.getUsersEmail(), cartDto.getItemNo());
		    if(findCart == null) {
		    	int sequence = sequence();
		    	cartDto.setCartNo(sequence);
		    	cartAdd(cartDto);
		    	return;
		    }
		  if( cartDto.getItemIoNo() == findCart.getItemIoNo()) { // 사이즈 같을경우만 qty
			    int qty = cartDto.getCartQty(); //추가할수량
			    int findqty = findCart.getCartQty(); //기존장바구니수량
			    int plus = qty + findqty;
			    changeQty(plus,findCart.getCartNo());
			    return;
		  }
		  int sequence = sequence();		//사이즈다른경우 
		    cartDto.setCartNo(sequence);
		    cartAdd(cartDto);
	}

	//장바구니추가
	public void cartAdd(CartDto cartDto) {
		String sql = "insert into cart(cart_no, users_email, item_no, item_io_no, cart_qty) values (?, ?, ?, ? ,?)";
		Object[] data = {cartDto.getCartNo(), cartDto.getUsersEmail(), cartDto.getItemNo(), cartDto.getItemIoNo(),cartDto.getCartQty()};
		jdbcTemplate.update(sql,data);	
	}
	
	//이미 장바구니에 있는지 확인 ((((Exception 방지))))
	public CartDto findCart(String usersEmail, int itemNo) {
	    String sql = "select * from cart where users_email = ? and item_no = ?";
	    Object[] data = {usersEmail, itemNo};

	    List<CartDto> result = jdbcTemplate.query(sql, cartMapper, data);
	    return result.isEmpty() ? null : result.get(0);
	}

	
	//있다면 QTY만 변경 (없으면 장바구니추가) 필요시 오버로드가능 / 장바구니내에서 qty변경가능
	public void changeQty(int cartQty, int cartNo) {
		String sql = "update cart set cart_qty = ? where cart_no = ?";
		Object[] data = {cartQty, cartNo};
		jdbcTemplate.update(sql,data);
	}
	
	//장바구니 아이템 삭제
	public boolean deleteCart(int itemNo, String usersEmail) {
		String sql = "delete from cart where cart_no=? and users_email=?";
		Object[] data = {itemNo, usersEmail};
		return jdbcTemplate.update(sql,data)>0;
		}
	
	//카트개수
	public int countCart(String usersEmail) {
		String sql = "select count(*) from cart where users_email=?";
		Object[] data = {usersEmail};
		return jdbcTemplate.queryForObject(sql, int.class,data);
	}
	
	//목록 
	public List<CartJoinVO> cartList(String usersEmail, PageVO pageVO) {
	    String sql = "select * from("
	    		+ "   select rownum rn, TMP.* from("
	    		+ "         select "
	    		+ "	c.cart_no, c.users_email, c.item_no, c.item_io_no ,c.cart_qty,"
	    		+ "	i.item_title, i.item_gender, i.item_category, i.item_detail,"
	    		+ "	i.item_like, i.item_color, i.item_price, i.item_content,"
	    		+ "	o.size_name"
	    		+ "	from cart c"
	    		+ "	left outer join item i on c.item_no = i.item_no"
	    		+ "	left outer join item_io o on c.item_io_no = o.item_io_no"
	    		+ "	where c.users_email = ? "
	    		+ "	order by c.cart_no desc"
	    		+ "   )TMP"
	    		+ ") where rn between ? and ?";
	    Object[] params = {usersEmail, pageVO.getStartRownum(), pageVO.getFinishRownum()};
	    return jdbcTemplate.query(sql, cartJoinMapper, params);
	}
	//단일찾기
	public CartJoinVO cartItem(int cartNo) {
	    String sql = "select "
	    		+ "	c.cart_no, c.users_email, c.item_no, c.item_io_no, c.cart_qty,"
	    		+ "	i.item_title, i.item_gender, i.item_category, i.item_detail,"
	    		+ "	i.item_like, i.item_color, i.item_price, i.item_content,"
	    		+ "	o.size_name"
	    		+ " from cart c"
	    		+ " left outer join item i on c.item_no = i.item_no"
	    		+ " left outer join item_io o on c.item_io_no = o.item_io_no"
	    		+ " where c.cart_no = ?";
	    Object[] data = {cartNo};
	    List<CartJoinVO> list = jdbcTemplate.query(sql, cartJoinMapper, data);
	    return list.isEmpty() ? null : list.get(0);
	}
	//vo에추가 
	public List<CartJoinVO> cartItemList(List<Integer>cartNoList){
		List<CartJoinVO>cartItemList = new ArrayList<>();
		for(int cartNo : cartNoList) {
			CartJoinVO cartVO = cartItem(cartNo);
			cartItemList.add(cartVO);
		}
		return cartItemList;
	}

}
