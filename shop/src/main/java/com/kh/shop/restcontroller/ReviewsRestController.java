package com.kh.shop.restcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.ReviewsDao;
import com.kh.shop.dao.ReviewsListViewDao;
import com.kh.shop.dto.ReviewsDto;
import com.kh.shop.dto.ReviewsListViewDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.service.AttachmentService;
import com.kh.shop.vo.ItemVO;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/reviews")
public class ReviewsRestController {
	
	@Autowired
	private ReviewsDao reviewsDao;
	
	@Autowired
	private ReviewsListViewDao reviewsListViewDao;
	
	@Autowired
	private AttachmentService attachmentService;

	
	@RequestMapping("/list") 	//+더보기할까바 item가져옴
	public Map<String,Object> list(ItemVO itemVO,@RequestParam int itemNo){
		List<ReviewsListViewDto> list = reviewsListViewDao.selectList(itemNo, itemVO); //1
		int count = reviewsDao.count(itemNo); //댓글수
		Map<String,Object> result = new HashMap<>();
		result.put("list", list); //리스트
		result.put("count", count); //댓긁슈
		return result;
	}
	
	
	//파일+텍트스---->...좀아닌거같슴
	//ajax를 json 방식으로 요청보낸다면 달라질수있음
	@PostMapping("/add")
	public Map<String, Object> add(@RequestParam String reviewsTitle,
												@RequestParam String reviewsContent,
												@RequestParam Integer reviewsStar,
												@RequestParam int itemNo,
												@RequestParam(required = false) List<MultipartFile> attach,
												HttpSession session
											) throws IllegalStateException, IOException {
												
		Map<String, Object> result = new HashMap<>();

		String usersEmail = (String) session.getAttribute("usersEmail");
		if (usersEmail == null) {
			throw new TargetNotFoundException("로그인 해주세요");
		}

		int reviewsNo = reviewsDao.sequence();

		// DTO 수동 세팅
		ReviewsDto reviewsDto = new ReviewsDto();
		reviewsDto.setReviewsNo(reviewsNo);
		reviewsDto.setUsersEmail(usersEmail);
		reviewsDto.setReviewsTitle(reviewsTitle);
		reviewsDto.setReviewsContent(reviewsContent);
		reviewsDto.setReviewsStar(reviewsStar);
		reviewsDto.setItemNo(itemNo);

		reviewsDao.insert(reviewsDto);

		// 첨부파일
		if (attach != null && !attach.isEmpty()) {
			List<Integer> attachList = attachmentService.saveList(attach);
			for (int attachmentNo : attachList) {
				reviewsDao.connect(reviewsNo, attachmentNo);
			}
		}
		//결과보냄
		result.put("success", true);
		return result;
	}

	@PostMapping("/delete")
	public Map<String,Object> delete(@RequestParam int reviewsNo) {
		Map<String, Object> result = new HashMap<>();
		try {
			List<Integer>attachmentList = reviewsDao.findAttachments(reviewsNo);
			if (attachmentList != null && !attachmentList.isEmpty()) {
				for(int attachment : attachmentList) {
					attachmentService.delete(attachment);
				}
			 }
			reviewsDao.delete(reviewsNo);
		}
		catch(Exception e) {	
			result.put("success", false);
		}  
		result.put("success", true);
		return result;
	}
}
