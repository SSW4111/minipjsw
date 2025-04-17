package com.kh.shop.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.shop.aop.AdminInterceptor;
import com.kh.shop.aop.UeresLoginInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{

	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Autowired
	private UeresLoginInterceptor usersLoginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//투비컨티뉴.. 
		
		//회원 전용 기능
		registry.addInterceptor(usersLoginInterceptor)
						.addPathPatterns(List.of(
							"/mypage/**",
							"/reviews/**"
						))
						.excludePathPatterns(List.of(
							"/users/join",
							"/users/joinFinish",
							"/users/login"
							
						));
		
		//관리자 전용 기능
//		registry.addInterceptor(adminInterceptor)
//						.addPathPatterns("/admin/**");
		
		
	}
}
