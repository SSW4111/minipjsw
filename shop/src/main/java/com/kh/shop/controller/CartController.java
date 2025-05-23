package com.kh.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.shop.dao.CartDao;
import com.kh.shop.dto.CartDto;
import com.kh.shop.vo.PageVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartDao cartDao;
	
	@RequestMapping("/main")
	public String cart(@ModelAttribute ("pageVO") PageVO pageVO, HttpSession session,
							Model model) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		List<CartDto> list = cartDao.cartList(usersEmail, pageVO);
		model.addAttribute("list", list);
		return "/WEB-INF/views/cart/main.jsp";
	}
}
