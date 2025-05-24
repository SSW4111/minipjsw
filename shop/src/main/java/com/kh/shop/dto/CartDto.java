package com.kh.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CartDto {
	private int cartNo;
	private String usersEmail;
	private int itemNo;
	private int itemIoNo;
	private int cartQty;

	
}
