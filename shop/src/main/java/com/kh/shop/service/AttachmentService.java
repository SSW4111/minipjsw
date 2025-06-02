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
import com.kh.shop.dao.ItemDao;
import com.kh.shop.dto.AttachmentDto;
import com.kh.shop.dto.ItemImagesDto;
import com.kh.shop.error.TargetNotFoundException;
import com.kh.shop.vo.ItemUpdateVO;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private ItemDao itemDao;
	
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
//				throw new TargetNotFoundException("파일이 존재하지 않습니다");
			}
			
			byte[] data = FileUtils.readFileToByteArray(target);
			return data;
		}

//		public byte[] loadAndSave(List<Integer>attachmentList) {
//			try {
//				for(int attachmentNo : attachmentList) {
//					AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
//					if(attachmentDto == null) {
//						AttachmentDto newAttach = save(attchmentNo);ㅅㅂ폼데이터
//					}
//				}
//			}
//			catch(Exception e) {
//				
//			}
//		}
//		
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
			
			return attachList;
		}
		
		
		//아이템 수정 처리
		public void attachUpdate(int itemNo, List<MultipartFile>files,  List<Integer>attachNoList) throws IllegalStateException, IOException {
			// 1.원래 어태치리스트
	    List<AttachmentDto> originAttachList = new ArrayList<>();
	    for(int attchNo : attachNoList) {
	    	originAttachList.add(attachmentDao.selectOne(attchNo)); 
	    }
	    
	    for (MultipartFile newFile : files) {
	        boolean isSame = false;
	        for (AttachmentDto origin : originAttachList) {
	            if (origin.getAttachmentName().equals(newFile.getOriginalFilename())) {
	                isSame = true;  // 같은 이름 파일 존재
	                break;
	            }
	        }
	        if (!isSame) {
	            //2. 새로 추가하는 파일 저장
	            Integer attachNo = save(newFile);
	            itemDao.connect(itemNo, attachNo);
	        }
	    }
	    //3. 이름으로 비교해서 삭제
	    List<String> newFileNames = new ArrayList<>();
	    for (MultipartFile file : files) {
	        newFileNames.add(file.getOriginalFilename());
	    }

	    for (AttachmentDto origin : originAttachList) {
	        if (!newFileNames.contains(origin.getAttachmentName())) {
	            attachmentDao.delete(origin.getAttachmentNo());
	        }
	    }
		}

		
		public void changeAttach(ItemUpdateVO vo) throws IllegalStateException, IOException {
			//기존 데이터 //여기는 아이템no랑 어태치 no만있음
			List<ItemImagesDto> noList = attachmentDao.findList(vo.getItemNo());
			//name뽑아낸 오리진리스트
			List<AttachmentDto> originList = new ArrayList<>();
			for(int i=0; i<noList.size() ; i++) {
				AttachmentDto origin = attachmentDao.selectOne(noList.get(i).getAttachmentNo());
				originList.add(origin);
			}
			
			//새로운파일네임저장리스트
			List<String> newFileNames = new ArrayList<>();
			for(MultipartFile file : vo.getFileList()) {
				//이름저장
				newFileNames.add(file.getOriginalFilename());
				 boolean isSame = false;
				  for (AttachmentDto origin : originList) {
			            if (origin.getAttachmentName().equals(file.getOriginalFilename())) {
			                isSame = true;  // 같은 이름 파일 존재
			                break;
			            }
			        }
			        if (!isSame) {
			            //2. 새로 추가하는 파일 저장
			            Integer attachNo = save(file);
			            itemDao.connect(vo.getItemNo(), attachNo);
			        }
			}
			
			//이름 안맞는거 삭제
			for(AttachmentDto origin : originList) {
				  if (!newFileNames.contains(origin.getAttachmentName())) {
					  attachmentDao.deleteImage(origin.getAttachmentNo());
			            attachmentDao.delete(origin.getAttachmentNo());
			        }
			}

		}
}
