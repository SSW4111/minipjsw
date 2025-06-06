package com.kh.shop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemIoDto {
	private int itemIoNo;
	private int itemIoTotal; //전체수량 ((수정불가))
	private String sizeName; //입고, 수정 
	private int itemNo; //itemNo
}
