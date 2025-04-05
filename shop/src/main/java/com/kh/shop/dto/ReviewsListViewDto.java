package com.kh.shop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ReviewsListViewDto {
	  private int reviewsNo;
	  private String reviewsTitle;
	  private String reviewsContent;
	  private Integer reviewsStar;
	  private Timestamp reviewsWtime;
	  private int itemNo;
	  private String itemColor;
	  private String itemTitle;
	  private String itemGender;
	  private String itemCategory;
	  private String itemDetail;
	  private String usersEmail;
	  private String usersLevel;
	  private String usersNickname;
	  
}
