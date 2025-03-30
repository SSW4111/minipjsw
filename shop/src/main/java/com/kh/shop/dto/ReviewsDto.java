package com.kh.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ReviewsDto {
  private int reviewsNo;
  private String reviewsTitle;
  private String reviewsContent;
  private Integer reviewsStar;
  private String usersEmail;
  private int itemNo;
}

