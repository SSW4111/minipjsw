package com.kh.shop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.CartDao;
import com.kh.shop.dto.CartDto;
import com.kh.shop.vo.CartJoinVO;
import com.kh.shop.vo.PageVO;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/rest/cart")
public class CartRestController {
	
	@Autowired
	private CartDao cartDao;
	//등록
	@PostMapping("/add")
	public ResponseEntity<String> add (@RequestBody CartDto cartDto, HttpSession session){
		try {
		String usersEmail = (String)session.getAttribute("usersEmail");
		 if (usersEmail == null) {	//401
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인안함");
	        }
		cartDto.setUsersEmail(usersEmail);
		cartDao.addOrUpdateCart(cartDto);
		return ResponseEntity.ok("ok");
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body("error");
		}
	}
	//삭제
	@PostMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam int cartNo, 
													HttpSession session){
		try {
			String usersEmail = (String)session.getAttribute("usersEmail");
			 if (usersEmail == null) {	//401
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인안함");
		        }
			cartDao.deleteCart(cartNo, usersEmail);
			return ResponseEntity.ok("ok");
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body("error");
		}
	}
	
	//qty업데이트
	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestParam int cartQty,
														@RequestParam int cartNo){
		try {
		cartDao.changeQty(cartQty, cartNo);
		return ResponseEntity.ok("ok");
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body("error");
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> list(PageVO pageVO, HttpSession session) {
	    try {
	        String usersEmail = (String) session.getAttribute("usersEmail");
	        if (usersEmail == null) {	//401
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인안함");
	        }

	        List<CartJoinVO> cartList = cartDao.cartList(usersEmail, pageVO);
	        return ResponseEntity.ok(cartList);
	    } catch (Exception e) {
//	    	 e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");
	    }
	}

}
