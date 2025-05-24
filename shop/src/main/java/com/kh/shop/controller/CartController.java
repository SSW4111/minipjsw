package com.kh.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.shop.dao.CartDao;
import com.kh.shop.service.CartItemService;
import com.kh.shop.vo.CartJoinVO;
import com.kh.shop.vo.PageVO;
import com.kh.shop.vo.SelectedItemVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartDao cartDao;

	@RequestMapping("/list")
	public String cart(@ModelAttribute ("pageVO") PageVO pageVO, HttpSession session,
							Model model) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		List<CartJoinVO> list = cartDao.cartList(usersEmail, pageVO);
		model.addAttribute("list", list);
		return "/WEB-INF/views/users/cartList.jsp";
	}
	
}
