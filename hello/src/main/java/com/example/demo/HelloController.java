package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping
	public ModelAndView hello() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","hello STS!!");
		mav.setViewName("hello");
		return mav;
	}
}
