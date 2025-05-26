package com.kh.shop.admin.restcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.AttachmentDao;
import com.kh.shop.dao.ItemDao;
import com.kh.shop.dto.AttachmentDto;
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
	
	@Autowired
	private AttachmentDao attachmentDao;
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

	//파일관련1 바이트배열이라 깨질수있다고함
//	@PostMapping("/item/attach")
//	public ResponseEntity<?> attach(@RequestParam List<Integer>attachmentList) {
//		try {
//		List<byte[]>list = new ArrayList<>();
//		for(int attachmentNo : attachmentList){
//			byte[] data = attachmentService.load(attachmentNo);
//			list.add(data);
//		}
//		return ResponseEntity.ok(list);
//		
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			throw new TargetNotFoundException("몰라");
//		}
//	}
//	
	//파일관련2 인코딩
	@PostMapping("/item/attach")
	public ResponseEntity<?> attach2(@RequestParam List<Integer> attachmentList) {
	    try {
	        List<String> base64List = new ArrayList<>();
	        for (int attachmentNo : attachmentList) {
	            byte[] data = attachmentService.load(attachmentNo);
	            String base64Encoded = Base64.getEncoder().encodeToString(data); // 인코딩
	            base64List.add(base64Encoded);
	        }
	        return ResponseEntity.ok(base64List);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new TargetNotFoundException("에러");
	    }
	}
	


}
