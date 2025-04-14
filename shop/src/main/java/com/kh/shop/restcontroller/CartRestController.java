package com.kh.shop.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.CartDao;
import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.vo.MorePageVO;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/cart")
public class CartRestController {
	
	@Autowired
	private CartDao cartDao;
	
	@RequestMapping("/list")
	public Map<String,Object> list(MorePageVO morePageVO, HttpSession session){
		String usersEmail = (String)session.getAttribute("usersEmail");
		List<ItemDetailViewDto>list = cartDao.cartList(usersEmail, morePageVO);
		int count = cartDao.countCart(usersEmail);
		
		Map <String,Object>result = new HashMap<>();
		result.put("list", list);
		result.put("totalCount", count); //카트 안 개수 
		return result;
	}
	
	@RequestMapping("/add")
	public Map<String,Object> add(@RequestParam int itemNo, HttpSession session){
		String usersEmail = (String)session.getAttribute("usersEmail");
		boolean have = cartDao.cartCheck(usersEmail, itemNo);//ㅠhave..zㅋㅋ
		Map<String,Object> result = new HashMap<>();
		if(have == false) { //만약 장바구니에 없는템이라면 ㅠ
			cartDao.cartInsert(itemNo, usersEmail);
			result.put("have", false); //새로추가 하는겁니다
		}
		else {
			result.put("have", true); //이미 가지고 있습니다
		}
		return result;
	}

	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam int itemNo, HttpSession session){
		String usersEmail = (String)session.getAttribute("usersEmail");
		cartDao.deleteCart(itemNo, usersEmail);
		Map<String,Object> result = new HashMap<>();
		result.put("success", true);
		return result;
	}
	//트라이캐치 심심하면 쓰러옴
}
