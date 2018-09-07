package com.yc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//后台界面显示
@Controller
public class Index {
	//首页显示
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	//其他页面显示
	@RequestMapping("/{page}")
	public String showAllPages(@PathVariable String page){
		return page;
	}
}
