package com.kh.shop.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.shop.dao.DeliveryDao;
import com.kh.shop.dto.DeliveryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class delivery {
//되는거확인
	@Autowired
	private DeliveryDao deliveryDao;
//	@Test
//	public void test() {
//		deliveryDao.insert(DeliveryDto.builder()
//				.deliveryNo(15)
//				.usersEmail("sagosago@goo.com")
//				.deliveryPost("123456")
//				.deliveryAddress1("부평구헬게헬")
//				.deliveryAddress2("300호")
//				.deliveryType("자택")
//				.build());
//	}
//	@Test
//	public void testdelete() {
//		deliveryDao.delete(15);
//	}
//	
//	@Test
//	public void updateTest() {
//		deliveryDao.update(DeliveryDto.builder()
//				.deliveryNo(15)
//				.deliveryPost("123456")
//				.deliveryAddress1("부평부평임")
//				.deliveryAddress2("500호")
//				.deliveryType("회사")
//				.build());
//	}
}
