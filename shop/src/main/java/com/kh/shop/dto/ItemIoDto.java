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
	private int itemIoIn; //입고 ((수정가능))
	private Timestamp itemIoInTime; // 입고시간 컹쓰 등록/수정때 변함
	private int itemIoOut; //출고 ((수정가능))
	private Timestamp itemIoOutTime; //회원이 구매할 때 만 변경 
	private String sizeName; //입고, 수정 
	private int itemNo; //itemNo
	public int getTotal() { 
	  return itemIoIn - itemIoOut;
	    }
	
}
