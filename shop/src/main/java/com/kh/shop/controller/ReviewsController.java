package com.kh.shop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.ReviewsDao;
import com.kh.shop.dto.ReviewsDto;
import com.kh.shop.service.AttachmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reviews")
public class ReviewsController {

	@Autowired
	private ReviewsDao reviewsDao;
	
	@Autowired
	private AttachmentService attachmentService;
	//이미지 주소 흠,,
	@RequestMapping("/images")
	public String images(@RequestParam int reviewsNo,Model model) {
		List<Integer> attachmentList = reviewsDao.findAttachments(reviewsNo);
		model.addAttribute("attachList",attachmentList);
		return "";
	}
	//등록
	@GetMapping("/add")
	public String addReviews() {
		return"/WEB-INF/views/reviews/add.jsp";
	}
	@PostMapping("/add")
	public String addReviews(@ModelAttribute ReviewsDto reviewsDto,
									@RequestParam List<MultipartFile>attach,
									@RequestParam int itemNo, HttpSession session) throws IllegalStateException, IOException {
		int reviewsNo = reviewsDao.sequence();
		reviewsDto.setReviewsNo(reviewsNo);
		session.getAttribute("userEmail");
		reviewsDto.setUsersEmail("userEmail");
		reviewsDto.setItemNo(itemNo);
		reviewsDao.insert(reviewsDto);
		
		if(!attach.isEmpty()){
			List<Integer>attachList = attachmentService.saveList(attach);
			for(int attachmentNo : attachList) {
				reviewsDao.connect(reviewsNo, attachmentNo);
			}
		}
		return "redirect:/item/detail?itemNo="+itemNo;
	}
	//삭제
	@PostMapping("/delete")
	public String delete(@RequestParam int reviewsNo) {
		try {
			List<Integer>attachmentList = reviewsDao.findAttachments(reviewsNo);
			for(int attachment : attachmentList) {
				attachmentService.delete(attachment);
			}
		}
		catch(Exception e) {}
		reviewsDao.delete(reviewsNo);
		return "";//미아
	}
	
	//수정 사진 생각하고함
	
	//리스트는 레스트쓸거같아서보류
}
