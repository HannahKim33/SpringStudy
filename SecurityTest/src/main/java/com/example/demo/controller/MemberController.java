package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	
	@GetMapping("/login") //post 방식의 처리는 security가 알아서 해줌
	public void login() {
		
	}
	
	@GetMapping("/service1")
	public void service1(HttpSession session) {
		//인증된(로그인한) 회원의 정보를 갖고 오기 위해 먼저 시큐리티의 인증객체가 필요하다.
		Authentication authentication=
				SecurityContextHolder.getContext().getAuthentication();
		
		//이 인증객체를 통해서 인증된(로그인한) User 객체를 받아온다.
		User user=(User) authentication.getPrincipal();
		
		//이 인증된 User를 통해서 로그인한 회원의 아이디를 갖고온다.
		String id=user.getUsername();
		
		//이 정보를 세션에 상태유지한다.
		//만약 id뿐만 아니라 로그인한 회원의 다른 정보도 필요하다면 dao를 통해 회원의 정보를 가져와서 상태유지한다.
		session.setAttribute("id", id);
	}
	
	@GetMapping("/service2")
	@ResponseBody
	public String service2() {
		return "서비스2입니다.";
	}
}