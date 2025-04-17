package com.kh.shop.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kh.shop.dao.ItemDao;
import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.ItemDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.vo.MorePageVO;

import jakarta.servlet.http.HttpSession;



@RestController
@CrossOrigin
@RequestMapping("/rest/wish")
public class WishRestController {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private ItemDao itemDao;
	//리스트 
	@RequestMapping("/list")
	public Map<String,Object> list(MorePageVO morePaveVO,  HttpSession session){
	String usersEmail = (String)session.getAttribute("usersEmail");
		if(usersEmail == null) {
			throw new TargetNotFoundException("로그인 해주세요");
		}
		
//	String usersEmail = "testuser@goog.com";
	int totalCount = usersDao.wishCount(usersEmail, morePaveVO);
	morePaveVO.setCount(totalCount);
	List<ItemDto> list = usersDao.itemLikeList(usersEmail, morePaveVO);

	Map<String, Object> result = new HashMap<>();
	result.put("list",list); //리스트
	result.put("totalCount",totalCount);  //총 위시개수
	result.put("isLastPage", morePaveVO.isLastPage());
	
	return result;
	}
	
	//이걸 굳이 또 들고와야하는지는 모르겠지만 ....
	//체크는 어차피 필요없고 좋아요/해제 만 있으면 되겠죠 개수도 필요하면 따가면됌
	//count == (나의 장바구니 목록개수)
	
	//좋아요/해제
	@RequestMapping("/action")
	public Map<String,Object> action(@RequestParam int itemNo, HttpSession session){
		String usersEmail = (String)session.getAttribute("usersEmail");
		if (usersEmail == null) {
		    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다");
		}
		boolean done = itemDao.checkItemLike(usersEmail, itemNo);
		if(done) {
			itemDao.deleteItemLike(usersEmail, itemNo);
		}
		else {
			itemDao.itemLike(usersEmail, itemNo);
		}
		int count = itemDao.countItemLike(itemNo);
		Map<String,Object> result = new HashMap<>();
		result.put("done", !done);
		result.put("count", count);
		return result;
	}
		
	
}
