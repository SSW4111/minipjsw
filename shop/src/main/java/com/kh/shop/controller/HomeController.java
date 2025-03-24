package com.kh.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {

	@RequestMapping("/")
	public String home() {
		
		return "/WEB-INF/views/home.jsp";
	}
}
