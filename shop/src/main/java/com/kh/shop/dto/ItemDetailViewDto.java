package com.kh.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemDetailViewDto {
	private int itemNo;
	private String itemColor;
	private String itemSize;
	private String itemTitle;
	private String itemContent;
	private String itemGender;
	private String itemCategory;
	private String itemDetail;
	private float itemAveStar;
	private int itemReviewsCount;
	private int itemIoNo;
	private int itemIoTotal;

}
