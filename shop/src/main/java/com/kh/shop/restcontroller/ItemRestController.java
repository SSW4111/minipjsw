package com.kh.shop.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
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

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/item")
public class ItemRestController {
	
	@Autowired
	private ItemDao itemDao;

	
	@Autowired
	private ItemListViewDao itemListViewDao;

	//시간남으면 최적화해서 한번에 보낼수있나 생각해봄..
//	@RequestMapping("/list")
//	public Map<String, Object> listAll(ItemVO itemVO){
//		List<ItemListViewDto> list = itemListViewDao.selectList(itemVO);
//		boolean isLastPage = itemVO.isLastPage();
//		
//		//전달할 목록많아진다면 map변경고려
//		Map<String,Object> result = new HashMap<>();
//		result.put("list", list);
//		result.put("isLastPage", isLastPage);
//		return result;
//	}
	

	@RequestMapping("/listM")
	public Map<String, Object> listM(ItemVO itemVO){
		List<ItemListViewDto> list = itemListViewDao.selectListMen(itemVO);
	
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

	@RequestMapping("/search")
	public Map<String, Object> search(ItemVO itemVO){
		List<ItemListViewDto> list = itemListViewDao.selectListMen(itemVO);
		int count = itemListViewDao.count(itemVO);
		itemVO.setCount(count);
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
	
//	@RequestMapping("/listW")
//	public Map<String,Object> listW(ItemVO itemVO){
//		List<ItemListViewDto> list = itemListViewDao.selectListF(itemVO);
//		boolean isLastPage = itemVO.isLastPage();
//		Map<String,Object> result = new HashMap<>();
//		result.put("listW", list); //이름 헷갈리면 그냥 다바꿔도됌ㅠ미안할뿐;
//		result.put("isLastPage", isLastPage);
//		Map<Integer, List<Integer>> li = new LinkedHashMap<>();
//		for(ItemListViewDto itemDto : list) {
//			li.put(itemDto.getItemNo(), itemDao.findAttachments(itemDto.getItemNo()));
//		}
//		result.put("attachmentList", li);
//		//System.out.println("result +========= " + result);
//		//return list;
//		return result;
//	}
	
	//좋아요
	@RequestMapping("/check")
	public Map<String,Object> check(@RequestParam int itemNo, HttpSession session){
		String usersEmail = (String)session.getAttribute("usersEmail");
		boolean done = itemDao.checkItemLike(usersEmail, itemNo);
		int count = itemDao.countItemLike(itemNo);
		
		Map<String,Object> result = new HashMap<>();
		result.put("done", done);
		result.put("count", count);
		return result;
	}
	
	//좋아요/해제 ->개수갱신
	@RequestMapping("/action")
	public Map<String,Object> action(@RequestParam int itemNo, HttpSession session){
		String usersEmail = (String)session.getAttribute("usersEmail");
		if (usersEmail == null) {
		    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다");
		}
		boolean done = itemDao.checkItemLike(usersEmail, itemNo);
		if(done) {
			itemDao.deleteItemLike(usersEmail, itemNo);
		}
		else {
			itemDao.itemLike(usersEmail, itemNo);
		}
		int count = itemDao.countItemLike(itemNo);
		Map<String,Object> result = new HashMap<>();
		result.put("done", !done);
		result.put("count", count);
		return result;
	}
		
}
