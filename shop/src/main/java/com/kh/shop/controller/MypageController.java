package com.kh.shop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.UsersDto;
import com.kh.shop.service.AttachmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private AttachmentService attachmentService;
	


	//마이페이지
	@GetMapping("/home")
	public String mypage(Model model, HttpSession session) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		UsersDto usersDto = usersDao.selectOne(usersEmail);
		model.addAttribute("userDto",usersDto); 
		return "/WEB-INF/views/mypage/home.jsp";
	}
	//조인안함 url반환
	@RequestMapping("/image")
	public String image(@RequestParam String usersEmail) {
		try {
		int attachmentNo = usersDao.findAttachment(usersEmail);
		return "redirect:/attachment/download?attachmentNo="+attachmentNo;
		}
		catch(Exception e) {
			return "redirect:http://placehold.co/100x100?text=P";
		}
	}

	//변경페이지;
	@GetMapping("/change")
	public String change(Model model,HttpSession session) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		UsersDto usersDto = usersDao.selectOne(usersEmail);

		String phone = usersDto.getUsersContact().replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
		usersDto.setUsersContact(phone);
		model.addAttribute("usersDto",usersDto); 

		Integer attachmentNo = usersDao.findAttachment(usersEmail); 
		model.addAttribute("attachmentNo",attachmentNo);

		return "/WEB-INF/views/mypage/change.jsp";
	}
	
	//변경
	@PostMapping("/change")
	public String change(@ModelAttribute UsersDto usersDto, HttpSession session, @RequestParam String usersPw,
			@RequestParam(required = false) MultipartFile usersProfile,
			@RequestParam(required = false, defaultValue = "false")boolean changeProfile) throws IllegalStateException, IOException {
		String usersEmail = (String)session.getAttribute("usersEmail");
		UsersDto findDto = usersDao.selectOne(usersEmail); //유저정보
		
		boolean isValid = usersPw.equals(findDto.getUsersPw());  //비번확인
		
		if(!isValid) {
			return "redirect:change?error";
			}
		else { //change ing--
			if(changeProfile == true) {//프사변경요청 3가지 존재
				Integer originAttachNo = usersDao.findAttachment(usersEmail); //파일찾고
				Integer newAttachNo = attachmentService.save(usersProfile); //새거 저장하고
				//1.기존 프로필 사진이 있을경우는 넘버를 찾아서 삭제후 새로운 번호 save
				//3.기존 프로필 사진이 있는유저가 삭제요청
				//2.기존 프로필 사진이 없을경우 그냥 업로드
				if(originAttachNo != null) {//기존 사진이 있을경우 1번,3번처리
					if(newAttachNo == -1) { //새로운프로필업승ㄹ경우  //아이거 return -1 이라 좀 불안하네 꼭확인필요
						attachmentService.delete(originAttachNo); //그냥삭제
					}
					else { //프로필있는자식지우고 프로필연결
						attachmentService.delete(originAttachNo);
						usersDao.connect(newAttachNo, usersEmail);
					}
				}
				else {
					usersDao.connect(newAttachNo, usersEmail);
				}
				
			}

		findDto.setUsersContact(usersDto.getUsersContact());
		findDto.setUsersNickname(usersDto.getUsersNickname());
		usersDao.update(findDto);
		return "redirect:home";
		}
		}
	
	//탈퇴
	@GetMapping("/exit")
	public String exit() {
		return "/WEB-INF/views/mypage/exit.jsp";
	}
	
	@PostMapping("/exit")
	public String exit(HttpSession session, @RequestParam String usersPw ) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		UsersDto usersDto = usersDao.selectOne(usersEmail);
		boolean isValid = usersPw.equals(usersDto.getUsersPw());
		if(!isValid) {
			return "redirect:exit?error";
			}
		else {
			int attachmentNo = usersDao.findAttachment(usersEmail);
			attachmentService.delete(attachmentNo); //사진삭제 
			usersDao.delete(usersEmail);
			session.removeAttribute(usersEmail);
		return "redirect:exit-Finish";
		}
	}
	
}
