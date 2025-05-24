package com.kh.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.shop.dao.CartDao;
import com.kh.shop.dao.DeliveryDao;
import com.kh.shop.dao.ItemDetailViewDao;
import com.kh.shop.dto.DeliveryDto;
import com.kh.shop.dto.ItemDetailViewDto;
import com.kh.shop.vo.CartJoinVO;
import com.kh.shop.vo.ItemDetailSelectVO;
import com.kh.shop.vo.SelectedItemVO;

@Service
public class CartItemService {

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private DeliveryDao deliveryDao;
	
	@Autowired
	private ItemDetailViewDao itemDetailViewDao;
	//VO매핑
	public SelectedItemVO cartItemList(List<Integer>cartNoList, String usersEmail) {
		List<CartJoinVO>cartJoinVoList = cartDao.cartItemList(cartNoList);
		int count = deliveryDao.count(usersEmail);
		List<DeliveryDto> deliveryList = deliveryDao.selectUserDelivery(usersEmail);
		
		SelectedItemVO selectedItemVO = SelectedItemVO.builder()
				.deliveryCount(count)
				.itemList(cartJoinVoList)
				.deliveryList(deliveryList)
				.build();
		return selectedItemVO;
		
	}
	
	//아이템디테일에서 단건으로 오는거 매핑
	public ItemDetailSelectVO itemList(int itemNo, int itemQty, String usersEmail) {
		List<DeliveryDto> deliveryList = deliveryDao.selectUserDelivery(usersEmail);
		ItemDetailViewDto itemDetail = itemDetailViewDao.selectList(itemNo);
		ItemDetailSelectVO selectItem = ItemDetailSelectVO.builder()
					.item(itemDetail)
					.qty(itemQty)
					.deliveryList(deliveryList)
					.build();
		return selectItem;
	}
	
}
