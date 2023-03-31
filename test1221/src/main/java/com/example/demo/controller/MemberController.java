package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MemberController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@GetMapping("/member/login")
	public void loginForm() {	
//		memberDAO.save(
//				new Member("kim", 
//				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kim"), 
//				"kim", 
//				"user", 
//				null));
//		memberDAO.save(
//				new Member("lee", 
//				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("lee"), 
//				"lee", 
//				"user", 
//				null));
	}
	
	
//	@PostMapping("/member/login")
//	public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
//		String msg = "";
//		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
//		Optional<Member> option = memberDAO.findById(id);
//		if(option.isPresent()) {
//			String dbPwd = option.get().getPwd();
//			if(pwd.equals(dbPwd)) {
//				session.setAttribute("id", id);				
//			}else {				
//				mav.setViewName("redirect:/member/login");
//			}
//		}else {			
//			mav.setViewName("redirect:/member/login");
//		}
//		
//		return mav;
//	}
}






