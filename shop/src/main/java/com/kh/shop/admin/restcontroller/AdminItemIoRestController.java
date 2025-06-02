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
