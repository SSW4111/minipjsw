package com.kh.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.shop.service.CartItemService;
import com.kh.shop.vo.ItemDetailSelectVO;
import com.kh.shop.vo.SelectedItemVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private CartItemService cartItemService;
	
		//장바구니에서 결제할 내역 보여주는리스트
		@GetMapping("/selectList-cart")
		public String selectItemListCart(HttpSession session, @RequestParam List<Integer>cartNoList,
											Model model) {
			String usersEmail = (String)session.getAttribute("usersEmail");
			SelectedItemVO selectedItemVO= cartItemService.cartItemList(cartNoList, usersEmail);
			model.addAttribute(selectedItemVO);
			return "/WEB-INF/views/cart/selectList.jsp";
		}
		
		//아이템디테일에서 결제할 내역 1개 보여주는리스트
		@GetMapping("/selectList")
		public String selectItemList(HttpSession session, 
											@RequestParam int itemNo,
											@RequestParam int itemQty,
											@RequestParam int itemIoNo,
												Model model) {
			String usersEmail = (String)session.getAttribute("usersEmail");
			ItemDetailSelectVO itemDetail = cartItemService.itemList(itemNo, itemQty, usersEmail,itemIoNo);
			model.addAttribute(itemDetail);
			return "/WEB-INF/views/cart/selectList.jsp";
		}
		
		
}
