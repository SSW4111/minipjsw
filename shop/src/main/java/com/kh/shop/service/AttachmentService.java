package com.kh.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dao.AttachmentDao;
import com.kh.shop.dto.AttachmentDto;
import com.kh.shop.error.TargetNotFoundException;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;
	
	//여기 바꾼거 C드라이브뿐 난 집에 D가없음 :`(
	//저장
	public int save(MultipartFile attach) throws IllegalStateException, IOException {
		if(attach.isEmpty()) return -1;
		
		File dir = new File("C:/uploadDir");
		if(!dir.exists())dir.mkdirs();
		
		int attachmentNo = attachmentDao.sequence();
		File target = new File(dir,String.valueOf(attachmentNo));
		attach.transferTo(target);
		
		AttachmentDto attachmentDto = new AttachmentDto();
		attachmentDto.setAttachmentNo(attachmentNo);
		attachmentDto.setAttachmentName(attach.getOriginalFilename());
		attachmentDto.setAttachmentType(attach.getContentType());
		attachmentDto.setAttachmentSize(attach.getSize());
		attachmentDao.insert(attachmentDto);
		return attachmentNo; //확인
	}
		//삭제
		public void delete(int attachmentNo) {
			File dir = new File("C:/uploadDir");
			File target = new File(dir,String.valueOf(attachmentNo));
			if(!target.isFile()) return;
			target.delete();
			
			attachmentDao.delete(attachmentNo);
		}
		
		public byte[] load(int attachmentNo) throws IOException {
			AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
			if(attachmentDto == null) {
				throw new TargetNotFoundException("존재하지 않는 파일번호");
			}
			
			File dir = new File("C:/uploadDir");
			File target = new File(dir, String.valueOf(attachmentNo));
			if(!target.isFile()) {
				throw new TargetNotFoundException("파일이 존재하지 않습니다");
			}
			
			byte[] data = FileUtils.readFileToByteArray(target);
			return data;
		}
		
		//여러장ㅡ_ㅡ
		public List<Integer> saveList(List<MultipartFile> attach) throws IllegalStateException, IOException {
			List<Integer> attachList = new ArrayList<>();
			if(attach.isEmpty()) return new ArrayList<>();
			File dir = new File("C:/uploadDir");
			if(!dir.exists())dir.mkdirs();
			
			for(MultipartFile file : attach) {
				int attachmentNo = attachmentDao.sequence();
				 File target = new File(dir, String.valueOf(attachmentNo));
		         file.transferTo(target);
		     	AttachmentDto attachmentDto = new AttachmentDto();
				attachmentDto.setAttachmentNo(attachmentNo);
				attachmentDto.setAttachmentName(file.getOriginalFilename());
				attachmentDto.setAttachmentType(file.getContentType());
				attachmentDto.setAttachmentSize(file.getSize());
				attachmentDao.insert(attachmentDto);
				attachList.add(attachmentNo);
			}
			
			return attachList;//파일수반환
		}
}
