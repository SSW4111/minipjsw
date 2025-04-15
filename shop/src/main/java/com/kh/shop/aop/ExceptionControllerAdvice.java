package com.kh.shop.aop;

import javax.naming.NoPermissionException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kh.shop.error.TargetNotFoundException;

//퍼옴... 주소는 대강ㄱㄱ..
@ControllerAdvice(annotations = {Controller.class})
public class ExceptionControllerAdvice {

	@ExceptionHandler(NoPermissionException.class)
	public String noPermission(NoPermissionException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "/WEB-INF/views/error/no-permission.jsp";
	}

	@ExceptionHandler(TargetNotFoundException.class)
	public String notFound(TargetNotFoundException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "/WEB-INF/views/error/notfound.jsp";
	}
@ExceptionHandler(Exception.class)
public String error(Exception e,Model model) {
	e.printStackTrace();
	model.addAttribute("message",e.getMessage());
	return"/WEB-INF/views/error/etc.jsp";
}


}



