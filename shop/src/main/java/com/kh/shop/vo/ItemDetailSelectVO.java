package com.kh.shop.vo;

import java.util.List;

import com.kh.shop.dto.DeliveryDto;
import com.kh.shop.dto.ItemDetailViewDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ItemDetailSelectVO {
	//디테일에서 바로구매요청시 보여줄 목록
	//아이템(매핑때문에 빌드하는거 단일 너무아닌거같아서 리스트로쓰고 0번씀)
	private ItemDetailViewDto item;
	//qty
	private int qty;
	//배송지
	private List<DeliveryDto> deliveryList;
	//size
	private String sizeName;
}
