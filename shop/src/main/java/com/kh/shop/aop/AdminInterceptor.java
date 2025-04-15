package com.kh.shop.aop;

import javax.naming.NoPermissionException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Service
public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, 
									HttpServletResponse response, Object hadler) throws NoPermissionException {
		HttpSession session = request.getSession();
		String usersLevel = (String) session.getAttribute("usersLevel");
		if (usersLevel == null) {// 비회원
			throw new NoPermissionException("로그인 필요합니다");
		}
		if (usersLevel.equals("관리자") == false) {
			throw new NoPermissionException("권한이 부족합니다");
		}
		return true;
	}
}
