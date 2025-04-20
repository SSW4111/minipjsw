package com.kh.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UpdateVO {
	private String sizeName;
	private String ioIn;
	private String itemNo;
}
