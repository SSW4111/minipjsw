package com.kh.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.shop.dao.DeliveryDao;
import com.kh.shop.dao.ItemDetailViewDao;
import com.kh.shop.dto.DeliveryDto;
import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.vo.SelectedItemVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/select")
public class SelectedItemController {
	
	@Autowired
	private ItemDetailViewDao itemDetailViewDao;
	
	@Autowired
	private DeliveryDao deliveryDao;
	
	@RequestMapping("/main")
	public String main(@RequestParam List<Integer>itemNoList, HttpSession session,
								Model model) {
		String usersEmail = (String)session.getAttribute("usersEmail");
		List<ItemDetailViewDto> list =itemDetailViewDao.selectList(itemNoList);
		int count = deliveryDao.count(usersEmail);
		List<DeliveryDto> deliveryList = deliveryDao.selectUserDelivery(usersEmail);
		SelectedItemVO selectedItemVO = SelectedItemVO.builder()
														.deliveryCount(count)
														.itemList(list)
														.deliveryList(deliveryList)
														.build();
		model.addAttribute(selectedItemVO);
		return "/WEB-INF/views/select/main.jsp";
	}
	
}
