package com.kh.shop.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.shop.dao.AttachmentDao;
import com.kh.shop.dao.ItemDao;
import com.kh.shop.dto.AttachmentDto;
import com.kh.shop.service.AttachmentService;

@Controller
@RequestMapping("/attachment")
public class FileDownLoadController {
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ItemDao itemDao;
	
	@RequestMapping("/download")
	public ResponseEntity<ByteArrayResource> download(@RequestParam int attachmentNo) throws IOException{
		byte[] data = attachmentService.load(attachmentNo);
		AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
		
		//포장(Wrap)
		ByteArrayResource resource = new ByteArrayResource(data);
		
		//반환
		return ResponseEntity.ok()
							.header(HttpHeaders.CONTENT_ENCODING, "UTF-8")
							.header(HttpHeaders.CONTENT_TYPE, attachmentDto.getAttachmentType())
							.contentLength(attachmentDto.getAttachmentSize())
							.header(HttpHeaders.CONTENT_DISPOSITION, 
								ContentDisposition.attachment()
									.filename(attachmentDto.getAttachmentName(), 
													StandardCharsets.UTF_8)
								.build().toString()
							)
						.body(resource);
	}
	@RequestMapping("/download/item")
	public ResponseEntity<ByteArrayResource> downloadItem(@RequestParam int itemNo) throws IOException{
		int attachmentNo = itemDao.findAttachmentByItem(itemNo);
		
		byte[] data = attachmentService.load(attachmentNo);
		AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
		
		//포장(Wrap)
		ByteArrayResource resource = new ByteArrayResource(data);
		
		//반환
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_ENCODING, "UTF-8")
				.header(HttpHeaders.CONTENT_TYPE, attachmentDto.getAttachmentType())
				.contentLength(attachmentDto.getAttachmentSize())
				.header(HttpHeaders.CONTENT_DISPOSITION, 
						ContentDisposition.attachment()
						.filename(attachmentDto.getAttachmentName(), 
								StandardCharsets.UTF_8)
						.build().toString()
						)
				.body(resource);
	}
}
