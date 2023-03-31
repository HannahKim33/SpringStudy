package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

@Controller
@RequestMapping("/join")
public class InsertMemberController {
	
	@Autowired
	private MemberDAO dao;

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping
	public void form() {
		
	}
	
	@RequestMapping
	public ModelAndView submit(MemberVO m) {
		ModelAndView mav=new ModelAndView();
		System.out.println(m);
		int re=dao.insert(m);
		
		if(re<=0) {
			mav.addObject("msg", "회원 가입 실패");
			mav.setViewName("error");
		}else {
			mav.setViewName("redirect:/listBoard");
		}
		return mav;
	}
}
