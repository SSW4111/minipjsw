package com.kh.shop.aop;

import javax.naming.NoPermissionException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Service
public class UeresLoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, 
										HttpServletResponse response, Object handler) throws NoPermissionException {
		
		HttpSession session = request.getSession();
		String usersEmail = (String) session.getAttribute("usersEmail");
		
		if(usersEmail != null) {
			return true;
		}
		else {
			throw new NoPermissionException("로그인 후 이용 가능합니다");
		}
	}
}
