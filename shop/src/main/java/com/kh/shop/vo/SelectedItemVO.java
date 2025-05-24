package com.kh.shop.vo;

import java.util.List;

import com.kh.shop.dto.DeliveryDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SelectedItemVO {

	private int deliveryCount;
	private List<CartJoinVO> itemList;
	private List<DeliveryDto> deliveryList;
}
