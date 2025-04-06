package com.kh.shop.restcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.ItemDao;
import com.kh.shop.dao.ItemListViewDao;
import com.kh.shop.dto.ItemListViewDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.service.AttachmentService;
import com.kh.shop.vo.ItemVO;

@CrossOrigin
@RestController
@RequestMapping("/rest/item")
public class ItemRestController {
	
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ItemListViewDao itemListViewDao;
//근데 얘가 썸머노트 쓰나?
	//썸머노트파일1
	@PostMapping("/upoad")
	public int upload(@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		if(attach.isEmpty()) {
			throw new TargetNotFoundException("첨부파일이 없습니다");
		}
		return attachmentService.save(attach);
	}
	//섬모노트파일여러개
	@PostMapping("/uploads")
	public List<Integer> uploads(@RequestParam(value="attach")List<MultipartFile>attachList) throws IllegalStateException, IOException{
		List<Integer> numbers = new ArrayList<>();
		for(MultipartFile attach : attachList) {
			if(attach.isEmpty()) continue;
			int attachmentNo = attachmentService.save(attach);
			numbers.add(attachmentNo);
		}
		return numbers;
	}
	
	//시간남으면 최적화해서 한번에 보낼수있나 생각해봄..
	@RequestMapping("/list")
	public Map<String, Object> listAll(ItemVO itemVO){
		List<ItemListViewDto> list = itemListViewDao.selectList(itemVO);
		boolean isLastPage = itemVO.isLastPage();
		
		//전달할 목록많아진다면 map변경고려
		Map<String,Object> result = new HashMap<>();
		result.put("list", list);
		result.put("isLastPage", isLastPage);
		return result;
	}
	

	@RequestMapping("/listM")
	public Map<String, Object> listM(ItemVO itemVO){
		List<ItemListViewDto> list = itemListViewDao.selectListM(itemVO);
		boolean isLastPage = itemVO.isLastPage();
		Map<String,Object> result = new HashMap<>();
		result.put("listM", list); //이름 헷갈리면 그냥 다바꿔도됌ㅠ미안할뿐;
		result.put("isLastPage", isLastPage);
		Map<Integer, List<Integer>> li = new LinkedHashMap<>();
		for(ItemListViewDto itemDto : list) {
			li.put(itemDto.getItemNo(), itemDao.findAttachments(itemDto.getItemNo()));
		}
		result.put("attachmentList", li);
		//System.out.println("result +========= " + result);
		//return list;
		return result;
	}
	
	@RequestMapping("/listW")
	public Map<String,Object> listW(ItemVO itemVO){
		List<ItemListViewDto> list = itemListViewDao.selectListF(itemVO);
		boolean isLastPage = itemVO.isLastPage();
		Map<String,Object> result = new HashMap<>();
		result.put("listW", list); //이름 헷갈리면 그냥 다바꿔도됌ㅠ미안할뿐;
		result.put("isLastPage", isLastPage);
		Map<Integer, List<Integer>> li = new LinkedHashMap<>();
		for(ItemListViewDto itemDto : list) {
			li.put(itemDto.getItemNo(), itemDao.findAttachments(itemDto.getItemNo()));
		}
		result.put("attachmentList", li);
		//System.out.println("result +========= " + result);
		//return list;
		return result;
	}
}
