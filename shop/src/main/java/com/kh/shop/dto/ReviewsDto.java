package com.kh.shop.dto;

import java.sql.Timestamp;

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
  private Timestamp reviewsWtime;
  private String usersNickname;
  private int itemNo;
  private String usersEmail;
}

