package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.CartDto;
import com.kh.shop.mapper.CartMapper;
import com.kh.shop.vo.PageVO;


@Repository
public class CartDao {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//시퀀스
	public int sequence() {
		String sql="select cart_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	//장바구니에 있는지 확인하고 이미있으면 qty수량 Up 없다면 그냥 추가
	public void addOrUpdateCart(CartDto cartDto) {
	    CartDto findCart = findCart(cartDto.getUsersEmail(), cartDto.getItemNo());
		    if(findCart == null) {
		    	int sequence = sequence();
		    	cartDto.setCartNo(sequence);
		    	cartAdd(cartDto);
		    	return;
		    }
	    int qty = cartDto.getCartQty(); //추가할수량
	    int findqty = findCart.getCartQty(); //기존장바구니수량
	    int plus = qty + findqty;
	    changeQty(plus,findCart.getCartNo());
	    return;
	}

	//장바구니추가
	public void cartAdd(CartDto cartDto) {
		String sql = "insert into cart(cart_no, users_email, item_no, cart_qty) values (?, ?, ?, ? )";
		Object[] data = {cartDto.getCartNo(), cartDto.getUsersEmail(), cartDto.getItemNo(), cartDto.getCartQty()};
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
		String sql = "delete from cart where item_no=? and users_email=?";
		Object[] data = {itemNo, usersEmail};
		return jdbcTemplate.update(sql,data)>0;
		}
	
	//카트개수
	public int countCart(String usersEmail) {
		String sql = "select count(*) from cart where users_email=?";
		Object[] data = {usersEmail};
		return jdbcTemplate.queryForObject(sql, int.class,data);
	}
	
	//목록 아이템 전체조인함
	public List<CartDto> cartList(String usersEmail, PageVO pageVO) {
	    String sql = "select * from ( "
	               + "    select rownum rn, TMP.* "
	               + "    from ( "
	               + "        select c.*, i.* "
	               + "        from cart c "
	               + "        left outer join item i on c.item_no = i.item_no "
	               + "        where c.users_email = ? "
	               + "        order by c.cart_no desc "
	               + "    ) TMP "
	               + ") "
	               + "where rn between ? and ?";

	    Object[] params = {usersEmail, pageVO.getStartRownum(), pageVO.getFinishRownum()};
	    return jdbcTemplate.query(sql, cartMapper, params);
	}

}
