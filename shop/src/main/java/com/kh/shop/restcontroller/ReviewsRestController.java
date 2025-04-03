package com.kh.shop.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.ReviewsDao;
import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.ReviewsDto;
import com.kh.shop.dto.UsersDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.vo.ItemVO;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/reviews")
public class ReviewsRestController {
	
	@Autowired
	private ReviewsDao reviewsDao;
	
	@Autowired
	private UsersDao usersDao;
	
	//여기도 글쓰기 할거임?!
	@RequestMapping("/list") 	//+더보기할까바 item가져옴
	public Map<String,Object> list(ItemVO itemVO,
									HttpSession session){
		List<ReviewsDto> list = reviewsDao.selectList(itemVO); //1
		boolean isLastPage = itemVO.isLastPage(); //2
		//혹시몰라서 닉네임가져옴
		String usersEmail = (String) session.getAttribute("userEmail");
		if(usersEmail == null) {
			throw new TargetNotFoundException("로그인해야됌내가이거써야됌?");
		}
		UsersDto usersDto = usersDao.selectOne(usersEmail);
		String usersNickname = usersDto.getUsersNickname(); //3
		
		Map<String,Object> result = new HashMap<>();
		result.put("list", list);
		result.put("isLastPage", isLastPage);
		result.put("usersNickname", usersNickname);
		return result;
	}

}
