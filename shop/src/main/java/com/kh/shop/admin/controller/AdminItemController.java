package com.kh.shop.admin.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.ItemDao;
import com.kh.shop.dao.ItemIoDao;
import com.kh.shop.dao.ItemListViewDao;
import com.kh.shop.dto.ItemDto;
import com.kh.shop.dto.ItemListViewDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.service.AttachmentService;
import com.kh.shop.vo.AdminItemVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminItemController {

	@Autowired
	private ItemListViewDao itemListViewDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ItemIoDao itemIoDao;

	@RequestMapping("/item-list")
	public String itemList (@ModelAttribute("adminItemVO")AdminItemVO adminItemVO
											,Model model, HttpSession session) {
		String level = (String)session.getAttribute("usersLevel");
		if(level.equals("관리자") || level ==null) {
			throw new TargetNotFoundException("안돼요");
		}
		List<ItemListViewDto> list = itemListViewDao.adminItemList(adminItemVO);
		model.addAttribute("list",list);
//		System.out.println("highStar: " + adminItemVO.getHighStar());
//		System.out.println("isHighStar(): " + adminItemVO.isHighStar());
//		System.out.println("recent :" + adminItemVO.isRecent());
		return "/WEB-INF/views/admin/item-list.jsp";
	}
	
	//등록
	@GetMapping("/item-add")
	public String addItem(HttpSession session) {
		String level = (String)session.getAttribute("usersLevel");
		if(level.equals("관리자") || level ==null) {
			throw new TargetNotFoundException("안돼요");
		}
		return "/WEB-INF/views/admin/item-add.jsp";
	}
	//등록 
	//섬머노트는 rest
	@PostMapping("/item-add")
	public String addItem(@ModelAttribute ItemDto itemDto, 
            @RequestParam List<MultipartFile> attach) throws IllegalStateException, IOException {
		int itemNo = itemDao.sequence();
		//System.out.println(itemDao.findAttachments(18));
		itemDto.setItemNo(itemNo);
		itemDao.insert(itemDto);

		if (!attach.isEmpty()) { //없는경우는 없겠지만 그냥..
			// 리스트에서 하나씩빼서저장
		   List<Integer> attachList = attachmentService.saveList(attach); 
		   for (int attachmentNo : attachList) {
		       itemDao.connect(itemNo, attachmentNo);
		   }
		}
		
		return "redirect:addFinish"; 
		}
	
	//얘빼고 그냥 바로 다른곳 이동해도됌 
	@RequestMapping("/addFinish")
	public String addFinish() {
		return "/WEB-INF/views/admin/addFinish.jsp";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam int itemNo, Model model) {
		ItemDto itemDto = itemDao.selectOne(itemNo);
		if(itemDto == null) {
			throw new TargetNotFoundException("존재하지 않는 상품정보"); //404
		}
		model.addAttribute(itemDto);
		return "/WEB-INF/views/item/update.jsp";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute ItemDto itemDto) {
		int itemNo = itemDto.getItemNo();
		ItemDto originDto = itemDao.selectOne(itemNo);
		
		if(originDto == null) {
			throw new TargetNotFoundException("존재하지 않는 상품정보"); //db에 데이터없음
		}
		//content섬머노트 퍼옴 근데 상품설명이미지1개만쓸거면 반복문제거가능
		Set<Integer> before = new HashSet<>();
		Document beforeDocument = Jsoup.parse(originDto.getItemContent());
		//HTML 태그 하나를 다룰 수 있는 객체 element  
		for(Element element : beforeDocument.select(".summernote-img")) {
			int attachmentNo = Integer.parseInt(element.attr("data-attachment-no"));
			before.add(attachmentNo);
		}
		
		Set<Integer> after = new HashSet<>();
		Document afterDocument = Jsoup.parse(itemDto.getItemContent());
		for(Element element : afterDocument.select(".summernote-img")) {
			int attachmentNo = Integer.parseInt(element.attr("data-attachment-no"));
			after.add(attachmentNo);
		}
		//우리 이미지리스트 이렇게 ㅎ ㅏ면되지않을까?
		Set<Integer> minus = new HashSet<>(before);
		minus.removeAll(after);
		//구해진 차집합의 내용만큼 이미지를 제거
		for(int attachmentNo : minus) {
			attachmentService.delete(attachmentNo);
		}
		
		itemDao.update(itemDto);
		return "redirect:detail?itemNo="+itemDto.getItemNo();
		
	}

	//삭제
	@PostMapping("/delete")
	public String delete(@RequestParam int itemNo) {
		try {
		List<Integer> attachmentList = itemDao.findAttachments(itemNo);
		for(int attachment : attachmentList) {
				attachmentService.delete(attachment);
			}
		}
		catch(Exception e) {}
		itemDao.delete(itemNo);
		itemIoDao.delete(itemNo); //@@@@@@@@그냥 io도지워버리겟음니다
		return "redirect:list"; //바꼬주샘
	}
	
	
}
