package com.kh.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import com.kh.shop.dao.ItemDetailViewDao;
import com.kh.shop.dao.ItemListViewDao;
import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.dto.ItemDto;
import com.kh.shop.dto.ItemIoDto;
import com.kh.shop.dto.ItemListViewDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.service.AttachmentService;
import com.kh.shop.vo.ItemVO;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ItemListViewDao itemListViewDao;
	
	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private ItemDetailViewDao itemDetailviewDao;
	

//	@RequestMapping("/women-list")
//	public String listW(@ModelAttribute ("itemVO") ItemVO itemVO,Model model ) {
//		List<ItemListViewDto> listW = itemListViewDao.selectListF(itemVO);
//		model.addAttribute("listW",listW); //이름..그저 미안할뿐
//		int count = itemListViewDao.count(itemVO);
//		itemVO.setCount(count);
//		return "/WEB-INF/views/item/women-list.jsp";
//	}
//	
//	@RequestMapping("/men-list")
//	public String listM(@ModelAttribute ("itemVO") ItemVO itemVO, Model model) {
//		List<ItemListViewDto> listM = itemListViewDao.selectListM(itemVO);
//		model.addAttribute("listM",listM); //이름....
//		int count = itemListViewDao.count(itemVO);
//		itemVO.setCount(count);
//		return "/WEB-INF/views/item/men-list.jsp";
//	}
	@RequestMapping("/men")
	public String listMen(@ModelAttribute ("itemVO") ItemVO itemVO, Model model) {
		List<ItemListViewDto> listM = itemListViewDao.selectListMen(itemVO);
		List<Integer> attachList = new LinkedList<>();
		for(ItemListViewDto dto: listM) {
			attachList.add(itemDao.findAttachmentByItem(dto.getItemNo()));
		}
		model.addAttribute("listM",listM); //이름....
		int count = itemListViewDao.count(itemVO);
		model.addAttribute("attachList", attachList);
		itemVO.setCount(count);
		return "/WEB-INF/views/item/men.jsp";
	}
	 
	//사진리스트로 보내기
	@RequestMapping("/images")
	public String images(@RequestParam int itemNo, Model model){
		List<Integer> attachmentList = itemDao.findAttachments(itemNo);
		model.addAttribute("attachList",attachmentList);
		 return "redirect:/item/images?itemNo="+itemNo;//어캐넘김?
	}


	
	//상세는 view만들고 하꾸
	@GetMapping("/detail")
	public String detail(@RequestParam int itemNo, Model model) {
		ItemDetailViewDto itemDetailViewDto = itemDetailviewDao.selectOne(itemNo); //dto찾음
		List<ItemDetailViewDto> colorList =  itemDetailviewDao.selectColor(itemDetailViewDto);
		
		model.addAttribute("colorList", colorList);
		//System.out.println(colorList);
		
		List<Integer> colorNoList = new LinkedList<>();
		for(ItemDetailViewDto dto : colorList) {	
			colorNoList.add(itemDetailviewDao.selectColorNo(dto.getItemNo()));
		}
		model.addAttribute("colorNoList",colorNoList);
		
		List<Integer> attachList = itemDao.findAttachments(itemNo);
		model.addAttribute("attachList",attachList);
		


		//itemIo list부르고
		List<ItemIoDto> iolist = itemDetailviewDao.selectIoList(itemNo);
		for (ItemIoDto io : iolist) {
		    io.setItemIoTotal(io.getTotal());
		}
		model.addAttribute("iolist",iolist);
		
		//SizeList 
		List<ItemDetailViewDto> sizeList =  itemDetailviewDao.selectSize(itemNo);
		model.addAttribute("sizeList",sizeList);   //사이즈리스트
		model.addAttribute("itemDetailViewDto",itemDetailViewDto); // dto넘김
		return "/WEB-INF/views/item/detail.jsp";
	}
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("/detail")
//	public String detail(@RequestParam int itemNo, Model model) {
//
//		ItemDetailViewDto itemDetailViewDto1 = itemDetailviewDao.selectOne(itemNo);
//		List<ItemDetailViewDto> colorList =  itemDetailviewDao.selectColor(itemDetailViewDto1);
//		int attachmentNo = itemDetailviewDao.findAttachment(itemNo); // itemDetailViewDto의 이미지
//		model.addAttribute("itemDetailViewDto",itemDetailViewDto1);
//		model.addAttribute("attachmentNo",attachmentNo);
//		Map<Integer,Integer> atta = new LinkedHashMap<>();
//		for(ItemDetailViewDto color : colorList) {
//			Integer attachNo = itemDetailviewDao.findAttachment(color.getItemNo());
//		//	Integer attachNo = null;
//			Integer imgNo = color.getItemNo();
//			
//			//System.out.println("칼라 넘버" + color.getItemNo());
//			if(attachNo != null) {
//				atta.put(imgNo,attachNo);				
//			}
//		}
//		model.addAttribute("colorListAtta",atta);
//		model.addAttribute("colorList",colorList);
//
//		ItemDetailViewDto itemDetailViewDto = itemDetailviewDao.selectOne(itemNo); //dto찾음
//	//	List<ItemDetailViewDto> colorList =  itemDetailviewDao.selectColor(itemDetailViewDto);
//		model.addAttribute("colorList",colorList);
//		//itemIo list부르고
//		List<ItemIoDto>iolist = itemDetailviewDao.selectIoList(itemNo);
//		List<ItemIoDto>iolist999 = new ArrayList<ItemIoDto>();
//		for(ItemIoDto io : iolist) { //리스트 쪼개서 gettotal 불러서 in - out 하고
//			io.setItemIoTotal(io.getTotal()); 
//			for(int i=0; i<iolist.size(); i++) {  //다시합침... 
//				iolist999.add(io); 
//			}
//		}
//		//System.out.println(iolist999);
//		model.addAttribute("iolist",iolist999); //최종...
//		//SizeList 
//		List<ItemDetailViewDto> sizeList =  itemDetailviewDao.selectSize(itemNo);
//		model.addAttribute("sizeList",sizeList);   //사이즈리스트
////		System.out.println("size =" + sizeList.size());
//	//	System.out.println("itemDetailViewDto"+ itemDetailViewDto);
//		
//		model.addAttribute("itemDetailViewDto",itemDetailViewDto); // dto넘김
////		System.out.println("color = "+colorList);
//
//
//		return "/WEB-INF/views/item/detail.jsp";
//	}
	
	
	
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
	

}
