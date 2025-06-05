package com.kh.shop.vo;

import java.sql.Timestamp;
import java.util.List;

import com.kh.shop.dto.ItemIoLogDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class IoLogVO {
	//io log
	private long itemIoLogNo;
	private String itemIoLogEvent;
	private Timestamp itemIoLogTime;
	private int itemIoNo;
	private int itemIoLogNumber;
	
	private int itemIoTotal; //전체수량 ((수정불가))
	private String sizeName; 
	private int itemNo; 

}
