package com.kh.shop.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
		System.out.println("CONTROLLER LOGIN");
		
		if (findDto ==null) {
			return false;
		}
		boolean isValid = findDto.getUsersPw().equals(usersDto.getUsersPw());
		if(isValid) {
			session.setAttribute("usersEmail", findDto.getUsersEmail());
			session.setAttribute("usersLevel",findDto.getUsersLevel());
			return true;
		}
		return false;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("usersEmail");
	    session.removeAttribute("usersLevel");
	    session.invalidate(); 
	}
	
	@PostMapping("/image-save")
	public void imageSave() {
		
	}
	
	// 로그인 확인 매핑
	@GetMapping("/checkLogin")
	public Map<String, Boolean> checkLogin(HttpSession session) {
	    // 세션에서 usersEmail 속성이 있는지 확인 (있으면 로그인한 상태)
	    boolean loggedIn = session.getAttribute("usersEmail") != null;
	    
	    // 로그인 상태를 JSON 응답으로 반환
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("loggedIn", loggedIn);
	    return response;
	}

}
























