package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import lombok.Setter;

@Controller
@Setter
public class IndexController {
	
	@Autowired
	private MemberDAO dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("/index");
	}
	
	@GetMapping("/login")
	public void login() {
	}
	
	@GetMapping("/signUp")
	public void signUpForm() {
		
	}
	
	@PostMapping("/signUp")
	public ModelAndView signUpSubmit(MemberVO m) {
		ModelAndView mav=new ModelAndView("redirect:/login");
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		int re=-1;
		re=dao.insert(m);
		if (re!=1) {
			mav.setViewName("/all/error");
			mav.addObject("msg","회원가입 실패");
		}
		return mav;
	}
	
}
