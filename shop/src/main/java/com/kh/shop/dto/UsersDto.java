package com.kh.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UsersDto {
	private String usersEmail;
	private String usersPw;
	private String usersContact;
	private String usersLevel;
	private String usersNickname;
	private Integer usersDelivery;

}
