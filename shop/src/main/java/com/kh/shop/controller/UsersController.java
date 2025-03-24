package com.kh.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.UsersDto;
import com.kh.shop.mapper.AttachmentMapper;
import com.kh.shop.mapper.UsersMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	//로직 postjoin1>2>3 >모두 세션에 하나하나 저장하고 마지막 form받을때 Dto불러서 insert 처리
	//만약에 중간에 가입 취소시 세션 삭제 remove말고 그머시기..여튼간에 이건 내일함
	//여기에 나중에 아이디검사,pw검사, 등등등하면 되지않을까?라는생각...유지보수는 구린거같긴해요
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
	//아여기 콘택트아니라 프로필이였네 내일수정하겠음 우리 콘택트 안받고 진행합니까?
	@PostMapping("/join3")
	public String join3(@RequestParam String usersNickname, @RequestParam String usersContact,
							HttpSession session) {
		session.setAttribute("usersNickname", usersNickname);
		session.setAttribute("usersContact", usersContact);
		
		//Dto불러와서 다저장
		UsersDto usersDto = new UsersDto();
		usersDto.setUsersEmail((String)session.getAttribute("usersEmail"));
		usersDto.setUsersPw((String)session.getAttribute("usersPw"));
		usersDto.setUsersNickname((String)session.getAttribute("usersNickname"));
		usersDto.setUsersContact((String)session.getAttribute("usersContact"));
		
		usersDao.insert(usersDto);
		
		return "redirect:join-Finish";
		
	}
	
	@GetMapping("/joinFinish")
	public String joinFiinish() {
		return "/WEB-INF/views/users/join-Finish.jsp";
	}
	

}
