package com.kh.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
		model.addAttribute("listM",listM); //이름....
		int count = itemListViewDao.count(itemVO);
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

	
	//등록고민중(dto추가할거아니면 1라인씩밖에못받는다는 결론 fe컨 필요할듯?)
	@PostMapping("/add") //사진은 국룰 attach로 받음
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
	//등록완료 빼도됌 모달해도됌 whatever you wantzzz
	@RequestMapping("/addFinish")
	public String addFinish() {
		return "/WEB-INF/views/item/addFinish.jsp";
	}
	
	//상세는 view만들고 하꾸
	@GetMapping("/detail")
	public String detail(@RequestParam int itemNo, Model model) {

		ItemDetailViewDto itemDetailViewDto1 = itemDetailviewDao.selectOne(itemNo);
		List<ItemDetailViewDto> colorList =  itemDetailviewDao.selectColor(itemDetailViewDto1);
		int attachmentNo = itemDetailviewDao.findAttachment(itemNo); // itemDetailViewDto의 이미지
		model.addAttribute("itemDetailViewDto",itemDetailViewDto1);
		model.addAttribute("attachmentNo",attachmentNo);
		Map<Integer,Integer> atta = new LinkedHashMap<>();
		for(ItemDetailViewDto color : colorList) {
			Integer attachNo = itemDetailviewDao.findAttachment(color.getItemNo());
			Integer imgNo = color.getItemNo();
			
			//System.out.println("칼라 넘버" + color.getItemNo());
			if(attachNo != null) {
				atta.put(imgNo,attachNo);				
			}
		}
		model.addAttribute("colorListAtta",atta);
		model.addAttribute("colorList",colorList);

		ItemDetailViewDto itemDetailViewDto = itemDetailviewDao.selectOne(itemNo); //dto찾음
	//	List<ItemDetailViewDto> colorList =  itemDetailviewDao.selectColor(itemDetailViewDto);
		model.addAttribute("colorList",colorList);
		//itemIo list부르고
		List<ItemIoDto>iolist = itemDetailviewDao.selectIoList(itemNo);
		List<ItemIoDto>iolist999 = new ArrayList<ItemIoDto>();
		for(ItemIoDto io : iolist) { //리스트 쪼개서 gettotal 불러서 in - out 하고
			io.setItemIoTotal(io.getTotal()); 
			for(int i=0; i<iolist.size(); i++) {  //다시합침... 
				iolist999.add(io); 
			}
		}
		//System.out.println(iolist999);
		model.addAttribute("iolist",iolist999); //최종...
		//SizeList 
		List<ItemDetailViewDto> sizeList =  itemDetailviewDao.selectSize(itemNo);
		model.addAttribute("sizeList",sizeList);   //사이즈리스트
//		System.out.println("size =" + sizeList.size());
	//	System.out.println("itemDetailViewDto"+ itemDetailViewDto);
		
		model.addAttribute("itemDetailViewDto",itemDetailViewDto); // dto넘김
//		System.out.println("color = "+colorList);


		return "/WEB-INF/views/item/detail.jsp";
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
		return "redirect:list";
	}
	
	//기본수정 
	//얘는 나중에io붙으면 생각을 한번더 해봐야할거같은데 큼
	//만약에 재고까지 수정하게 되면
	//itemDetailView 기준으로 update 로직을 다시 짜야 함
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

}
