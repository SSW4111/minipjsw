package com.kh.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class DeliveryDto {
	private int deliveryNo;
	private String usersEmail;
	private String deliveryPost;
	private String deliveryAddress1;
	private String deliveryAddress2;
	private String deliveryType;

}
