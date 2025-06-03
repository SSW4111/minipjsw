package com.kh.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UsersOrderOptionsDto {
	private long usersOrderOptionsNo;
	private String usersOrderOptionsRequest;
	private String usersEmail;
}
