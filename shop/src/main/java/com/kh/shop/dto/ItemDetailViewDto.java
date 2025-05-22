package com.kh.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemDetailViewDto {
	private int itemNo;
	private String itemColor;
	private String itemTitle;
	private String itemContent;
	private String itemGender;
	private String itemCategory;
	private String itemDetail;
	private Float itemAveStar;
	private Integer itemReviewsCount;
	private int itemIoNo;
	private int itemIoTotal;
	private int itemLike;
	private String sizeName;
	private int itemPrice;
}
