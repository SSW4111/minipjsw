package com.kh.shop.dto;

import java.util.List;

import com.kh.shop.vo.UpdateVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UpdateRequestDto {
    private List<UpdateVO> itemIoDtoList;
    private String itemNo;

}