package com.kh.shop.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.shop.dto.ItemDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ItemUpdateVO {
	private List<MultipartFile> fileList;
	private int itemNo;
}
