package com.kh.shop.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemListViewDto2 {
	private String itemTitle;
	private String itemCategory;
	private String itemDetail;
	private List<Integer> itemNoList;
}
