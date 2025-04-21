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
import com.kh.shop.dto.ItemIoDto;
import com.kh.shop.dto.UpdateRequestDto;
import com.kh.shop.vo.MorePageVO;
import com.kh.shop.vo.UpdateVO;

@RestController
@CrossOrigin
@RequestMapping("/rest/admin/item-io")
public class AdminItemIoRestController {
	
	@Autowired
	private ItemIoDao itemIoDao;
	
	
	@PostMapping("/update")
	public ResponseEntity<?> handleUpdate(@RequestBody UpdateRequestDto dto) {
		ItemIoDto itemIoDto = new ItemIoDto();
	    System.out.println("itemNo: " + dto.getItemNo());
	    for (UpdateVO vo : dto.getItemIoDtoList()) {
	    	itemIoDto.setItemNo(Integer.parseInt(dto.getItemNo()));
	        System.out.println("size: " + vo.getSizeName() + ", ioIn: " + vo.getIoIn());
			itemIoDto.setSizeName(vo.getSizeName());
			int setIn =Integer.parseInt(vo.getIoIn());
			itemIoDto.setItemIoIn(setIn);
			//itemIoDto.setItemIoTotal(itemIoDto.getItemIoIn() + itemIoDto.getItemIoTotal());
			boolean valid = itemIoDao.updateAsIn(itemIoDto);
			System.out.println(itemIoDto);
	    }
	    return ResponseEntity.ok("받음");
	}
	
	
	
	
	
	
	//io수정
//	@PostMapping("/update")
//	public boolean update(@RequestBody List<UpdateVO> itemIoDtoList, @RequestParam int itemNo){
//		ItemIoDto itemIoDto = new ItemIoDto();
//		
//		System.out.println(itemIoDtoList);
//		if(itemIoDtoList == null) return false;
//		System.out.println("digy");
//		for(UpdateVO vo : itemIoDtoList) {
//			System.out.println(vo.getSizeName());
//			System.out.println(vo.getIoIn());
//			//itemIoDto.setSizeName(vo.getSizeName());
//			//itemIoDto.setItemIoIn(vo.getIoIn());
//			//itemIoDto.setItemIoTotal(itemIoDto.getItemIoIn() + itemIoDto.getItemIoTotal());
//			//itemIoDao.updateAsIn(itemIoDto);
//		}
//		    return true;
//	}
	
	//io리스트임 얘까지만 테스트완 나머지는 내일..
	//관리자관련 aop나중에함
	@RequestMapping("/list")
//	public Map<String,Object> list(int itemNo,MorePageVO morePageVO ){
		public List<ItemIoDto> list(@RequestParam int itemNo,MorePageVO morePageVO ){
	//jsp = 총합: ${dto.total}
		List<ItemIoDto> list = itemIoDao.selectList(itemNo, morePageVO);
//		boolean isLastPage = morePageVO.isLastPage();
//		
//		Map<String, Object>result = new HashMap<>();
//		result.put("list", list);
//		result.put("isLastPage", isLastPage);
		return list;
	}
	//하..얘네 깜빡하고 그냥 단일처리했는데 월요일ㅇ ㅔ 리스트로바꾸던지 할게유^_^
	//io인서트임
	 //io인서트임
//    @RequestMapping("/add")
//    public Map<String,Object>add(@ModelAttribute List<ItemIoDto> itemIoDtoList){
//
//        for(ItemIoDto itemIoDto : itemIoDtoList) {
//            int total = itemIoDto.getTotal();
//            itemIoDto.setItemIoTotal(total);
//            System.out.println("itemIoDto =" + itemIoDto);
//        }
//        
//        itemIoDao.insert(itemIoDto, );
//        
//        Map<String,Object>result = new HashMap<>();
//        result.put("success",true);
//        return result;
//    }
	
	
	@RequestMapping("/add")

	public boolean add(@ModelAttribute ItemIoDto dto) {
		int seq = itemIoDao.sequence();
		dto.setItemIoNo(seq);
		dto.setItemIoTotal(dto.getItemIoIn());
		boolean success = itemIoDao.insert(dto);
		return success;
	}
	

// 	public Map<String,Object> add(ItemIoDto itemIoDto){

		
// 			int itemIoNo = itemIoDao.sequence();
// 			itemIoDto.setItemIoNo(itemIoNo);	
// 			int total = itemIoDto.getTotal();
// 			itemIoDto.setItemIoTotal(total);
// 			itemIoDao.insert(itemIoDto);
			
	
// 		Map<String,Object>result = new HashMap<>();
// 		result.put("success",true);
// 		return result;
// 	}
	
// 	//io수정 터질확률 90퍼센트 
// 	@RequestMapping("/update")
// 	public Map<String, Object>update(@ModelAttribute List<ItemIoDto> itemIoDtoList){
// 		for(ItemIoDto itemIoDto : itemIoDtoList) {
// 			int total= itemIoDto.getTotal();
// 			itemIoDto.setItemIoTotal(total);
// 			itemIoDao.update(itemIoDto);
// 		}
// 		 Map<String, Object> result = new HashMap<>();
// 		    result.put("success", true);
// 		    return result;
// 	}

	
	@RequestMapping("/delete")
	public Map<String,Object>delete(@RequestParam String sizeName, @RequestParam int itemNo){
		itemIoDao.deleteOne(sizeName, itemNo);
		Map<String,Object> result = new HashMap<>();
		result.put("success", true);
		return result;
	}
}
