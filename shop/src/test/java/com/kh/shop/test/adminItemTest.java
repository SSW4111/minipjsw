package com.kh.shop.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.shop.dao.ItemDao;
import com.kh.shop.dto.ItemDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class adminItemTest {
	
	@Autowired
	private ItemDao itemDao;
	
//	@Test //수정 test
//	public void test() {
//		ItemDto itemDto = itemDao.selectOne(121);
//		itemDto.setItemTitle("티티메롱");
//		itemDto.setItemGender("F");
//		itemDto.setItemCategory("하의");
//		itemDto.setItemDetail("청바지");
//		itemDto.setItemColor("black");
//		itemDto.setItemPrice(15000);
//		itemDto.setItemContent("테스트2");
//		itemDao.update(itemDto);
//		log.debug("Dto={}",itemDto);
//	}
	
//	//삭제
//	@Test
//	public void test() {
//		itemDao.delete(121);
//	}
}
