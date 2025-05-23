package com.kh.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.shop.dao.DeliveryDao;
import com.kh.shop.dto.DeliveryDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.vo.MorePageVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	private DeliveryDao deliveryDao;
	
	//유저 한명 자기 주소 리스트보는거 잘나옴
	@GetMapping("/list")
	public String list(@ModelAttribute DeliveryDto deliveryDto, @RequestParam String usersEmail,
							Model model, MorePageVO morePageVO){
		List<DeliveryDto> list = deliveryDao.selectUserDelivery(usersEmail);
		int total = deliveryDao.count(usersEmail);
		morePageVO.setCount(total);
		model.addAttribute(list);
		System.out.println("list" + list);
		return "/WEB-INF/views/delivery/list.jsp";
	}
	
	@GetMapping("/add")
	public String add(@RequestParam String usersEmail) {
		return "/WEB-INF/views/delivery/list.jp";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute DeliveryDto deliveryDto, @RequestParam String usersEmail) {
		int deliveryNo = deliveryDao.sequence();
		deliveryDto.setDeliveryNo(deliveryNo);
		deliveryDto.setUsersEmail(usersEmail);
		deliveryDao.insert(deliveryDto);
		return "redirect:/delivery/add"+deliveryNo;
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam int deliveryNo) {
		deliveryDao.delete(deliveryNo);
		return "redirect:list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam int deliveryNo, Model model) {
		DeliveryDto deliveryDto = deliveryDao.selectOne(deliveryNo);
		if(deliveryDto == null) throw new TargetNotFoundException("존재하지 않는 주소");
		model.addAttribute(deliveryDto);
		return "/WEB-INF/views/delivery/update.jsp";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute DeliveryDto deliveryDto) {
		int deliveryNo = deliveryDto.getDeliveryNo();
		DeliveryDto originDto = deliveryDao.selectOne(deliveryNo);
		if(originDto == null)throw new TargetNotFoundException("존재하지 않는 주소");
		deliveryDao.update(deliveryDto);
		return "redirect:list";
	}
	
}
