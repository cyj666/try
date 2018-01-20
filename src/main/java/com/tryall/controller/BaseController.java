package com.tryall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {

	/**
	 * 动态跳转
	 * @param active
	 * @return
	 */
	@RequestMapping(value="/{active}",method=RequestMethod.GET)
	public String urlAction(@PathVariable("active") String active) {
		return active;
	}
}
