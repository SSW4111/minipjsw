package com.kh.shop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.UsersDto;
import com.kh.shop.mapper.UsersMapper;
import com.kh.shop.service.AttachmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private AttachmentService attachmentService;
	
	
	//로직 post join1>2>3 >모두 세션에 하나하나 저장하고 마지막 form받을때 Dto불러서 insert 처리
	//회원가입 완료시 invalidate 
	//중간에 가입취소시 모르겠다 30분뒤만료되니 알아서해 ..:`( 
	//회원가입------------------------------------------------------------------------------아 이거컨트롤러따로뺄까?고민(joincontroller)
	@GetMapping("/join1")
	public String join1() {
		return "/WEB-INF/views/users/join1.jsp";
	}

	@PostMapping("/join1")
	public String join1(@RequestParam String usersEmail, HttpSession session) {
		session.setAttribute("usersEmail", usersEmail);
		return "redirect:join2";
	}
	
	@GetMapping("/join2")
		public String join2(){
			return "/WEB-INF/views/users/join2.jsp";
		}
	
	
	@PostMapping("/join2")
	public String join2(@RequestParam String usersPw, HttpSession session) {
		session.setAttribute("usersPw",usersPw);
		return "redirect:join3";
		
	}
	
	@GetMapping("/join3")
	public String join3() {
		return"/WEB-INF/views/users/join3.jsp";
	}

	@PostMapping("/join3")
	public String join3(@RequestParam String usersNickname, @RequestParam String usersContact,
							HttpSession session, MultipartFile usersProfile)throws IOException {
		session.setAttribute("usersNickname", usersNickname);
		session.setAttribute("usersContact", usersContact);
		
		//빈세션 있나 체크
		boolean emptySession = (session.getAttribute("usersEmail") == null || session.getAttribute("usersPw") ==null
								||session.getAttribute("usersNickname") ==null || session.getAttribute("usersContact")==null );
		
		if(emptySession) {
			return "redirect:join3?error";
		}
		//Dto불러와서 다저장
		UsersDto usersDto = new UsersDto();
		usersDto.setUsersEmail((String)session.getAttribute("usersEmail"));
		usersDto.setUsersPw((String)session.getAttribute("usersPw"));
		usersDto.setUsersNickname((String)session.getAttribute("usersNickname"));
		usersDto.setUsersContact((String)session.getAttribute("usersContact"));
		usersDao.insert(usersDto);
		
		if(usersProfile != null && !usersProfile.isEmpty()){
			int attachmentNo = attachmentService.save(usersProfile);
			usersDao.connect(attachmentNo,usersDto.getUsersEmail());
		}
		
		session.invalidate();
		return "redirect:/";	
	}

	@GetMapping("/join-Finish")
	public String joinFinish() {
		return "/WEB-INF/views/users/join-Finish.jsp";
	}
	
	//--------------------------------------------------------------------------------------

	//로긘
//	@GetMapping("/login") 
//	public String login() {
//		return"/WEB-INF/views/users/login.jsp";
//	}
//	
//	
//	@PostMapping("/login")
//	public String login(@ModelAttribute UsersDto usersDto, HttpSession session) {
//		UsersDto findDto = usersDao.selectOne(usersDto.getUsersEmail());
//		if (findDto ==null) {
//			return "redirect:login?error";
//		}
//		boolean isValid = findDto.getUsersPw().equals(usersDto.getUsersPw());
//		if(isValid) {
//			session.setAttribute("usersEmail", findDto.getUsersEmail());
//			session.setAttribute("usersLevel",findDto.getUsersLevel());
//			
//			return "redirect:/";
//		}
//		return "redirect:login?error";
//	}
	
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("usersEmail");
//		return "redirect:/";
//	}
	
	
}


