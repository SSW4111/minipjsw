package com.kh.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UpdateVO2 {
	private String sizeName;
	private String ioIn; //이게 변경할 수량?
	private String itemNo;
	private String itemIoLogEvent;
}
