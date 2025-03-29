package com.kh.shop.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.UsersDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/users")
public class UsersRestController {
	
	@Autowired
	private UsersDao usersDao;
	
	@RequestMapping("/checkUsersEmail")
	public boolean checkUsersId(@RequestParam String usersEmail) {
		UsersDto usersDto = usersDao.selectOne(usersEmail);
		return usersDto == null;
	}
	
	@RequestMapping("/checkUsersNickname")
	public boolean checkUsersNickname(@RequestParam String usersNickname) {
		UsersDto usersDto = usersDao.selectOne(usersNickname);
		return usersDto == null;
	}
	
	@PostMapping("/login")
	public boolean login(@ModelAttribute UsersDto usersDto, HttpSession session) {
		
		String ema = usersDto.getUsersEmail();
		System.out.println(ema);
		UsersDto findDto = usersDao.selectOne(ema);


		System.out.println("dkssdsdfs");
		if (findDto ==null) {
			
			return false;
		}
		boolean isValid = findDto.getUsersPw().equals(usersDto.getUsersPw());
		if(isValid) {
			session.setAttribute("usersEmail", findDto.getUsersEmail());
			session.setAttribute("usersLevel",findDto.getUsersLevel());
			String em = (String) session.getAttribute("usersEmail");
					System.out.println("userEmail: em " +em);
			
			return true;
		}
		return false;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("usersEmail");
	    session.removeAttribute("usersLevel");
	}

}
























