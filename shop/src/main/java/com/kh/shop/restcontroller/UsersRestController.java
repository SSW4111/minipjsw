package com.kh.shop.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.UsersDto;

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

}
