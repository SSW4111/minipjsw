package com.kh.shop.controller;

import java.io.IOException;
import java.util.List;

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
import com.kh.shop.dto.ItemDto;
import com.kh.shop.service.AttachmentService;
import com.kh.shop.vo.ItemVO;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private AttachmentService attachmentService;

	
	
	@RequestMapping("/women-list")
	public String listW(@ModelAttribute ("itemVO") ItemVO itemVO,Model model ) {
		List<ItemDto> listW = itemDao.selectListF(itemVO);
		model.addAttribute("listW",listW); //이름..그저 미안할뿐
		int count = itemDao.count(itemVO);
		itemVO.setCount(count);
		return "/WEB-INF/views/item/women-list.jsp";
	}
	
	@RequestMapping("/man-list")
	public String listM(@ModelAttribute ("itemVO") ItemVO itemVO, Model model) {
		List<ItemDto> listM = itemDao.selectListM(itemVO);
		model.addAttribute("listM",listM); //이름....
		int count = itemDao.count(itemVO);
		itemVO.setCount(count);
		return "/WEB-INF/views/item/man-list.jsp";
	}
	
	//사진리스트로 보내기
	@RequestMapping("/images")
	public String images(@RequestParam int itemNo, Model model){
		List<Integer> attachmentNo = itemDao.findAttachments(itemNo);
		model.addAttribute("attachList",attachmentNo);
		 return "/WEB-INF/views/item/images.jsp";
	}
	
	//등록
	@GetMapping("/add")
	public String addItem() {
		return "/WEB-INF/views/item/add.jsp";
	}
	
	//등록고민중(dto추가할거아니면 1라인씩밖에못받는다는 결론 fe컨 필요할듯?)
	@PostMapping("/add") //사진은 국룰 attach로 받음
	public String addItem(@ModelAttribute ItemDto itemDto, 
            @RequestParam List<MultipartFile> attach) throws IllegalStateException, IOException {
		int itemNo = itemDao.sequence();
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
	//등록완료 빼도됌 모달해도됌 whatever you wantzzz
	@RequestMapping("/addFinish")
	public String addFinish() {
		return "/WEB-INF/views/item/addFinish.jsp";
	}
	
	//상세는 view만들고 하꾸
	
	
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
		return "redirect:list";
	}
	
	//수정
	
}
