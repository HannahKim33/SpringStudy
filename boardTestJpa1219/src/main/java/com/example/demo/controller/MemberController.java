package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@GetMapping("/member/login")
	public void loginForm() {
		
	}
	
	@PostMapping("/member/login")
	public ModelAndView loginSubmit(String id,String pwd, HttpSession session) {
		ModelAndView mav=new ModelAndView("redirect:/board/list/1");
		Optional<Member> option=dao.findById(id);
		if(option.isPresent()) {
			String dbPwd=option.get().getPwd();
			if(pwd.equals(dbPwd)) {
				session.setAttribute("id",id);
			}else {
				mav.setViewName("redirect:/member/login");
			}
		}else {
			mav.setViewName("redirect:/member/login");
		}
		return mav;
	}
}
