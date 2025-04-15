package com.kh.shop.admin.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.UsersDao;
import com.kh.shop.dto.UsersDto;
import com.kh.shop.vo.PageVO;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/admin/users")
public class AdminManagementRestController {

	@Autowired
	private UsersDao usersDao;
	@RequestMapping("/list")
	public Map<String,Object>list(HttpSession session,PageVO pageVO){
//		String level = (String)session.getAttribute("usersLevel");
		
		List<UsersDto>list = usersDao.selectList(pageVO);
		int count = usersDao.count(pageVO);
		Map<String,Object>result = new HashMap<>();
		result.put("list", list);
		result.put("totalCount", count);
		return result;
	}
}
