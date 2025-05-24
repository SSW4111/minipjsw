package com.kh.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CartJoinVO {
	//카트
	private int cartNo;
	private String usersEmail;
	private int itemNo;
	private int itemIoNo;
	private int cartQty;
	//아이템
	private String itemTitle;
	private String itemGender;
	private String itemCategory;
	private String itemDetail;
	private int itemLike;
	private String itemColor;
	private int itemPrice;
	private String itemContent;
	//io
	private String sizeName;

}
