
package com.kh.shop.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.shop.dao.ItemIoDao;
import com.kh.shop.dto.ItemIoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class itemIo {
	

	@Autowired
	private ItemIoDao itemIoDao;
	@Test
	public void test(){
		Timestamp systime = new Timestamp(System.currentTimeMillis());
	
	
	}
}
//package com.kh.shop.test;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.kh.shop.dao.ItemIoDao;
//import com.kh.shop.dto.ItemIoDto;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest
//public class itemIo {
//	
//
//	@Autowired
//	private ItemIoDao itemIoDao;
//	@Test
//	public void test(){
//		Timestamp systime = new Timestamp(System.currentTimeMillis());
//		List<ItemIoDto> itemIoDtoList = new ArrayList<>(Arrays.asList(
//			    new ItemIoDto(51, 0, 100,systime,0, null, "S", 118),
//			    new ItemIoDto(52, 0, 90, systime, 0, null, "L", 118),
//			    new ItemIoDto(53, 0, 80, systime, 10, null, "L", 118)
//			));

//		for(ItemIoDto itemIoDto : itemIoDtoList) {
//			int total = itemIoDto.getTotal();
//			itemIoDto.setItemIoTotal(total);
//			log.debug("입고: {}, 출고: {}", itemIoDto.getItemIoIn(), itemIoDto.getItemIoOut());
//			log.debug("총토탈: {}", itemIoDto.getTotal());
//			try {
//			    itemIoDao.insert(itemIoDto, itemIoDto.getItemNo());
//			log.debug("itemIoDto={}",itemIoDtoList);
//			} catch (Exception e) {
//				log.error("오류: {}", e.getMessage(), e);
//			}
//		}
//	
////		for(ItemIoDto itemIoDto : itemIoDtoList) {
////			int itemIoNo = itemIoDao.sequence();
////			itemIoDto.setItemIoNo(itemIoNo);	
////			int total = itemIoDto.getTotal();
////			itemIoDto.setItemIoTotal(total);
////			itemIoDao.insert(itemIoDto, itemNo);
////			
////		}
////		Map<String,Object>result = new HashMap<>();
////		result.put("success",true);
////		return result;
//	}
//	
//}