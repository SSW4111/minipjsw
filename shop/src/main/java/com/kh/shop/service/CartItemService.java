package com.kh.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.shop.dao.CartDao;
import com.kh.shop.dao.DeliveryDao;
import com.kh.shop.dto.DeliveryDto;
import com.kh.shop.vo.CartJoinVO;
import com.kh.shop.vo.SelectedItemVO;

@Service
public class CartItemService {

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private DeliveryDao deliveryDao;
	//VO매핑
	public SelectedItemVO cartItemList(List<Integer>cartNoList, String usersEmail) {
		List<CartJoinVO>cartJoinVoList = cartDao.cartItemList(cartNoList);
		int count = deliveryDao.count(usersEmail);
		List<DeliveryDto> DeliveryList = deliveryDao.selectUserDelivery(usersEmail);
		
		SelectedItemVO selectedItemVO = SelectedItemVO.builder()
				.deliveryCount(count)
				.itemList(cartJoinVoList)
				.deliveryList(DeliveryList)
				.build();
		return selectedItemVO;
		
	}
	
}
