package com.kh.shop.admin.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.ItemIoDao;
import com.kh.shop.dao.ItemIoLogDao;
import com.kh.shop.dto.ItemIoDto;
import com.kh.shop.dto.ItemIoLogDto;
import com.kh.shop.dto.UpdateRequestDto;
import com.kh.shop.dto.UpdateRequestDto2;
import com.kh.shop.vo.IoLogVO;
import com.kh.shop.vo.MorePageVO;
import com.kh.shop.vo.UpdateVO;

@RestController
@CrossOrigin
@RequestMapping("/rest/admin/item-io")
public class AdminItemIoRestController {
	
	@Autowired
	private ItemIoDao itemIoDao;
	
	@Autowired
	private ItemIoLogDao itemIoLogDao;
	
	@PostMapping("/update")
	public ResponseEntity<?> handleUpdate(@RequestBody UpdateRequestDto dto) {
		ItemIoDto itemIoDto = new ItemIoDto();
//	    System.out.println("itemNo: " + dto.getItemNo());
	    for (UpdateVO vo : dto.getItemIoDtoList()) {
	    	itemIoDto.setItemNo(Integer.parseInt(dto.getItemNo()));
			itemIoDto.setSizeName(vo.getSizeName());
			System.out.println(itemIoDto);
			
	    }
	    return ResponseEntity.ok("받음");
	}
	
	@PostMapping("/update2")
	public ResponseEntity<?> handleUpdate2(@RequestBody UpdateRequestDto2 dto) {
	    for (int i = 0; i < dto.getItemIoDtoList().size(); i++) {
	        int itemNo = Integer.parseInt(dto.getItemNo());
	        String sizeName = dto.getItemIoDtoList().get(i).getSizeName();
	        ItemIoDto findDto = itemIoDao.selectOne2(itemNo, sizeName);
	        //재입고?
	        if ("입고".equals(dto.getItemIoDtoList().get(i).getItemIoLogEvent())) {
	            int originT = findDto.getItemIoTotal();
	            int plus = Integer.parseInt(dto.getItemIoDtoList().get(i).getIoIn());
	            int newT = originT + plus;
	            findDto.setItemIoTotal(newT);
	            itemIoDao.update(findDto);
	        }
	        
	       if("차감".equals(dto.getItemIoDtoList().get(i).getItemIoLogEvent())) {
	    	   int originT = findDto.getItemIoTotal();
	    	   int minus = Integer.parseInt(dto.getItemIoDtoList().get(i).getIoIn());
	    	   int newT = originT - minus;
	    	   findDto.setItemIoTotal(newT);
	    	   itemIoDao.update(findDto);
	       }
	    }

	    return ResponseEntity.ok("ok");
	}

	
	//음수나 양수로 보낼경우
//	@PostMapping("/update2")
//	public ResponseEntity<?> handleUpdate2(@RequestBody UpdateRequestDto2 dto) {
//	    for (int i = 0; i < dto.getItemIoDtoList().size(); i++) {
//	        int itemNo = Integer.parseInt(dto.getItemNo());
//	        String sizeName = dto.getItemIoDtoList().get(i).getSizeName();
//	        ItemIoDto findDto = itemIoDao.selectOne2(itemNo, sizeName);
//
//	        String event = dto.getItemIoDtoList().get(i).getItemIoLogEvent();
//	        int originT = findDto.getItemIoTotal();
//	        int ioIn = Integer.parseInt(dto.getItemIoDtoList().get(i).getIoIn());
//
//	        // 절대값으로 처리 
//	        int changeAmount = Math.abs(ioIn);
//	        int newT = originT;
//
//	        if ("입고".equals(event)) {
//	            newT += changeAmount;
//	        } else if ("차감".equals(event)) {
//	            newT -= changeAmount;
//	        }
//
//	        findDto.setItemIoTotal(newT);
//	        itemIoDao.update(findDto);
//	    }
//
//	    return ResponseEntity.ok("ok");
//	}

	   
	
	
	
	@RequestMapping("/list")
		public List<ItemIoDto> list(@RequestParam int itemNo,MorePageVO morePageVO ){
		List<ItemIoDto> list = itemIoDao.selectList(itemNo, morePageVO);
		return list;
	}
	
	
	@RequestMapping("/add")
	public boolean add(@ModelAttribute ItemIoDto dto) {
		int seq = itemIoDao.sequence();
		dto.setItemIoNo(seq);
		boolean success = itemIoDao.insert(dto);
		int sequence = itemIoLogDao.sequence();
		return success;
	}
	
	@RequestMapping("/add2")
	public boolean add2(@ModelAttribute IoLogVO ioLogVO) {
		int seq = itemIoDao.sequence();
		ItemIoDto itemIoDto = new ItemIoDto();
		itemIoDto.setItemIoNo(seq);
		itemIoDao.insert(itemIoDto);
		int sequence = itemIoLogDao.sequence();
		ItemIoLogDto itemIoLogDto = ItemIoLogDto.builder()
				.itemIoNo(sequence)
				.itemIoLogEvent("입고")
				.itemIoNo(itemIoDto.getItemIoNo())
				.itemIoLogNumber(ioLogVO.getItemIoLogNumber())
				.build();
		boolean success = itemIoLogDao.insert(itemIoLogDto);
		return success;
	}
	
	@RequestMapping("/delete")
	public Map<String,Object>delete(@RequestParam String sizeName, @RequestParam int itemNo){
		itemIoDao.deleteOne(sizeName, itemNo);
		Map<String,Object> result = new HashMap<>();
		result.put("success", true);
		return result;
	}
	

	
	
}
