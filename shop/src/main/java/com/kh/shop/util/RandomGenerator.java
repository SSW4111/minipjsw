package com.kh.shop.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
	private Random r = new Random();
	
	public String randomNumber(int size) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(r.nextInt(9)+1);
		for(int i = 0 ; i < size - 1;i++) {
			buffer.append(r.nextInt(10));
		}
		return buffer.toString();
	}
	
}
