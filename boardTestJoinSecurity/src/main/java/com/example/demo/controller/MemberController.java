package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.Setter;

@Controller
@Setter
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	@GetMapping("/member/join")
	public void joinForm() {
		
	}
	
	@PostMapping("/member/join")
	public ModelAndView joinSubmit(Member m) {
		ModelAndView mav=new ModelAndView("redirect:/member/login");
		
//		String encPwd=passwordEncoder.encode(m.getPwd());
//		m.setPwd(encPwd);
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		
		try {
			dao.save(m);
		}catch(Exception e) {
			mav.addObject("msg","회원가입 실패");
			mav.setViewName("error");
		}
		
		/*
		dao.save(m);
		Optional<Member> obj=dao.findById(m.getId());
		if(obj.isPresent()) {
			mav.addObject("msg","회원가입 실패");
			mav.setViewName("error");
		}*/
		return mav;
	}
	
	@GetMapping("/member/login")
	public void loginForm() {
		
		dao.save(new Member("sist01",
		PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("sist"),
		"관리자1","admin",null));
		
		dao.save(new Member("sist02",
				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("sist"),
				"관리자2","admin",null));
		
		dao.save(new Member("kim",
				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kim"),
				"kim","user",null));
		dao.save(new Member("lee",
				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("lee"),
				"lee","user",null));
		
	}
	
//	@PostMapping("/member/login")
//	public ModelAndView loginSubmit(String id,String pwd, HttpSession session) {
//		ModelAndView mav=new ModelAndView("redirect:/board/list/1/all");
//		Optional<Member> option=dao.findById(id);
//		if(option.isPresent()) {
//			String dbPwd=option.get().getPwd();
//			if(pwd.equals(dbPwd)) {
//				session.setAttribute("id",id);
//			}else {
//				mav.setViewName("redirect:/member/login");
//			}
//		}else {
//			mav.setViewName("redirect:/member/login");
//		}
//		return mav;
//	}
}
