package com.kh.shop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemIoDto {
	private int itemIoNo; //itemNo
	private int itemIoTotal;
	private int itemIoIn;
	private Timestamp itemIoInTime;
	private int itemIoOut;
	private Timestamp itemIoOutTIme;
	private String sizeName;
	public int getTotal() {
	  return itemIoIn - itemIoOut;
	    }
	
}
