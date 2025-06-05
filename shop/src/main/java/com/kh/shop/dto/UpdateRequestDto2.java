package com.kh.shop.dto;

import java.util.List;

import com.kh.shop.vo.UpdateVO2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UpdateRequestDto2 {
    private List<UpdateVO2> itemIoDtoList;
    private String itemNo;

}