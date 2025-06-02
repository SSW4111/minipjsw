package com.kh.shop.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import com.kh.shop.dao.AttachmentDao;
import com.kh.shop.dao.ItemDao;
import com.kh.shop.dto.AttachmentDto;
import com.kh.shop.error.TargetNotFoundException;
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
	@GetMapping("/item")
	public ResponseEntity<byte[]> loadAttachment(@RequestParam int attachmentNo) throws IOException {
	    AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
	    if (attachmentDto == null) throw new TargetNotFoundException("파일 정보 없음");

	    byte[] data = attachmentService.load(attachmentNo);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType(attachmentDto.getAttachmentType()));

	    // 한글 파일명 UTF-8 URL 인코딩
	    String encodedFileName = UriUtils.encode(attachmentDto.getAttachmentName(), StandardCharsets.UTF_8);

	    // ASCII 범위 내 안전한 파일명 (한글은 _ 로 대체)
	    String asciiFileName = attachmentDto.getAttachmentName().replaceAll("[^\\x20-\\x7E]", "_");

	    // Content-Disposition 헤더에 filename과 filename* 같이 넣기
	    String contentDispositionValue = String.format(
	        "attachment; filename=\"%s\"; filename*=UTF-8''%s",
	        asciiFileName,
	        encodedFileName
	    );
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, contentDispositionValue);

	  //  System.out.println("Content-Disposition: " + contentDispositionValue);

	    return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}
	
	
	
}