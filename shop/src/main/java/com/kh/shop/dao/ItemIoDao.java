package com.kh.shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemDto;
import com.kh.shop.mapper.ItemIoMapper;

@Repository
public class ItemIoDao {

	@Autowired
	private ItemIoMapper itemIoMapper;
	public void insert(ItemDto itemDto) {
		
	}
}
