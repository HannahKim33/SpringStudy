package com.example.demo;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg",getGreeting());
		mav.setViewName("hello");	//경로와 확장자 생략. application.properties에 써줌
		
		return mav;
	}
	
	public String getGreeting() {
		int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(hour>=6 && hour<=10) {
			return "좋은 아침입니다.";
		}else if(hour>=12 && hour<=15){
			return "점심 식사는 하셨나요?";
		}else if(hour>=18 && hour<=22) {
			return "좋은 밤 되세요";
		}
		return "안녕하세요";
	}
}
