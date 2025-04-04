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

import com.kh.shop.dao.ItemDao;
import com.kh.shop.dao.ReviewsDao;
import com.kh.shop.dto.ReviewsDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.service.AttachmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reviews")
public class ReviewsController {

	@Autowired
	private ReviewsDao reviewsDao;
	
	@Autowired
	private ItemDao itemDao;
	
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
	public String addReviews(@RequestParam int itemNo,Model model) {
		model.addAttribute("itemDto",itemDao.selectOne(itemNo));
		return"/WEB-INF/views/reviews/add.jsp";
	}
	@PostMapping("/add")
	public String addReviews(@ModelAttribute ReviewsDto reviewsDto,
									@RequestParam List<MultipartFile>attach,
									@RequestParam int itemNo, HttpSession session) throws IllegalStateException, IOException {
		int reviewsNo = reviewsDao.sequence();
		reviewsDto.setReviewsNo(reviewsNo);
		String usersEmail = (String)session.getAttribute("userEmail");
		if(usersEmail == null) {
			throw new TargetNotFoundException("로그인해주세욘");
		}
		reviewsDto.setUsersEmail(usersEmail); 
		reviewsDto.setItemNo(itemNo); 
		reviewsDao.insert(reviewsDto);
		
		if(attach != null &&!attach.isEmpty()){
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
			if (attachmentList != null && !attachmentList.isEmpty()) {
				for(int attachment : attachmentList) {
					attachmentService.delete(attachment);
				}
			 }
			reviewsDao.delete(reviewsNo);
		}
		catch(Exception e) {
			e.printStackTrace(); //방어막 나중에삭제
		}  
		return "";//미아   
	}
	

//	@GetMapping("/update")
//	public String update(Model model, @RequestParam int reviewsNo) {
//		ReviewsDto reviewsDto = reviewsDao.selectOne(reviewsNo);
//		if(reviewsDto ==null) {
//			throw new TargetNotFoundException("존재하지 않는 후기입니다");
//		}
//		model.addAttribute("reviewDto",reviewsDto);
//		return "/WEB-INF/view/review/update.jsp";
//	}
//	
//	@PostMapping("/update")
//	public String update(@ModelAttribute ReviewsDto reviewsDto,
//								@RequestParam List<MultipartFile> attach) {
//		int reviewsNo = reviewsDto.getReviewsNo();
//		ReviewsDto originDto = reviewsDao.selectOne(reviewsNo);
//		//원래파일 - 새로업로드된파일(
//		
//	}
//	
	
	
	//리스트는 레스트쓸거같아서보류
}
