package org.ostenant.yoga.store.core.controller;

import java.util.Date;

import org.ostenant.yoga.store.core.bean.TestTb;
import org.ostenant.yoga.store.core.service.TestTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestTbController {

	@Autowired
	private TestTbService testTbService;

	@RequestMapping(value = "/testTb/add.do", method = RequestMethod.POST)
	public String testAdd(String name, Date birthday) {
		System.out.println("1");
		testTbService.add(new TestTb(name, birthday));
		return "";
	}
	
	@RequestMapping(value = "/testTb/query.do")
	public String testSelect(){
		String name = "陈林";
		Date birthday = new Date();
		testTbService.add(new TestTb(name, birthday));
		return "";
	}
}
