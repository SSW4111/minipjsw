package com.kh.shop.amin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.shop.dao.ItemListViewDao;
import com.kh.shop.dto.ItemListViewDto;
import com.kh.shop.vo.AdminItemVO;

@Controller
@RequestMapping("/admin")
public class AdminItemController {

	@Autowired
	private ItemListViewDao itemListViewDao;
	
	@RequestMapping("/item-list")
	public String itemList (@ModelAttribute("adminItemVO")AdminItemVO adminItemVO
											,Model model) {
		List<ItemListViewDto> list = itemListViewDao.adminItemList(adminItemVO);
		model.addAttribute("list",list);
//		System.out.println("highStar: " + adminItemVO.getHighStar());
//		System.out.println("isHighStar(): " + adminItemVO.isHighStar());
//		System.out.println("recent :" + adminItemVO.isRecent());
		return "/WEB-INF/views/admin/item-list.jsp";
	}
	
	//등록
	@GetMapping("/add")
	public String addItem() {
		return "/WEB-INF/views/admin/item-add.jsp";
	}
	
}
