package com.kh.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.UsersDto;
import com.kh.shop.mapper.UsersMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	private UsersDao usersDao;
	


	//마이페이지
	@GetMapping("/home")
	public String mypage(Model model, HttpSession session) {
		String usersEamil = (String)session.getAttribute("usersEmail");
		UsersDto usersDto = usersDao.selectOne(usersEamil);
		model.addAttribute("userDto",usersDto); 
		return "/WEB-INF/views/mypage/home.jsp";
	}

	//변경페이지;
	@GetMapping("/change")
	public String change(Model model,HttpSession session) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		UsersDto usersDto = usersDao.selectOne(usersEmail);
		model.addAttribute("userDto",usersDto); 
		return "/WEB-INF/views/mypage/change.jsp";
	}
	
	//변경
	@PostMapping("/change")
	public String change(@ModelAttribute UsersDto usersDto, HttpSession session, @RequestParam String usersPw) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		UsersDto findDto = usersDao.selectOne(usersEmail); //유저정보
		boolean isValid = usersPw.equals(findDto.getUsersPw()); 
		if(!isValid) {
			return "redirect:change?error";
			}
		else {
		findDto.setUsersContact(usersDto.getUsersContact());
		findDto.setUsersNickname(usersDto.getUsersNickname());
		usersDao.update(findDto);
		return "redirect:home";
		}
		}
	
	//탈퇴
	@GetMapping("/exit")
	public String exit() {
		return "/WEB-INF/views/mapage/exit.jsp";
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
			usersDao.delete(usersEmail);
			session.removeAttribute(usersEmail);
		return "redirect:exit-Finish";
		}
	}
	
}
