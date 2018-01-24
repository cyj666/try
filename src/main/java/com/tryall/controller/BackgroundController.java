package com.tryall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackgroundController {

	@RequestMapping("/adminLogin")
	public String adminLogin() {
		return "/background/adminLogin";
	}
}
