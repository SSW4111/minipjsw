package com.kh.shop.admin.restcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.ItemDao;
import com.kh.shop.dto.ItemDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.service.AttachmentService;

@RestController
@CrossOrigin
@RequestMapping("/rest/admin/item")
public class AdminItemRestController {
	
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ItemDao itemDao;
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
	
	@GetMapping("/delete")
	public boolean delete(@RequestParam int itemNo) {
		//ItemDto targetDto = itemDao.selectOne(itemNo);
		return itemDao.delete(itemNo);
	}

	
}
