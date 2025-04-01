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
	
	//수정 (사진로직)
	//<attachList> 찾기
	//1.사진 (삭제/추가요청시)선택한 파일만 지울수있을까.?---> 어떻게? true=가능 false=(내수준)불가능ㅋㅋ
	//2-true. 선택한 파일만 삭제 3.새파일 커넥하고 추가 -->안그려짐..
	//2.-false 원래있던 파일 no를 savelist에 넣어두고 3.새로운사진connect후 list에 넣기-->이건될거같은데
	//(백업리스트) (new리스트(구번호도들어가야하는데..))비교해서 차집합 날려버리고 리스트생성하면.. 
	//하지만 기존이미지는 이미처리끝나서 안들어오죠 ㅋ
	//배웠던거 set사용해서 차집합 하면? 
	// Set<> 지울번호가뭔지알고 ㅡㅡ
	// 결론---->애초에 말도안되는 로직
	//요청을 true/false만 받으면 이미처리된 이미지는 안들어오기때문에 새리스트가잡히는데
	//그럼 뭘지워야할지 찾을수가없음
	//delete img 번호든뭐든 정보를 받아야한다

	//위처럼하면 전부삭제후 새파일업로드해도 문제가없음
	//1.(사진 추가요청만)
	//2.원래있떤 리스트에 추가하면됌 
	//그렇다면 심화과정으로 요청을 true false 두개로받아서
	//그냥 true --->사진변경요청합니다 false -->내용만바굼니다
	//(삭제/추가) (사진추가만요청) 구분어려움
	//그럼 닥치고2-false 방식으로 구현 
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
