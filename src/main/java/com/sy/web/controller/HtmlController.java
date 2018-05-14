package com.sy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @deccription 页面
 *
 * @author zhanbiane
 * 2018年5月14日
 */
@Controller
public class HtmlController {

	@GetMapping("form1")
	public String form1() {
		return "form1";
	}
	
	@GetMapping("form2")
	public String form2() {
		return "form2";
	}
}
