package com.kh.shop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class IoListViewDto {
	  private String itemIoLogEvent;
	    private Timestamp itemIoLogTime;
	    private int itemIoLogNumber;
	    private String sizeName;
	    private int itemNo;
}
