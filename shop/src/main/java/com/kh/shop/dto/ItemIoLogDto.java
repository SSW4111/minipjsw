package com.kh.shop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemIoLogDto {
	private long itemIoLogNo;
	private String itemIoLogEvent;
	private Timestamp itemIoLogTime;
	private int itemIoNo;
	private int itemIoLogNumber;
}
