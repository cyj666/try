package com.tryall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandle {

	//异常处理
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processException(HttpServletRequest request,HttpServletResponse response,
			Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("login");
		return mv;
	}
	
	
}
