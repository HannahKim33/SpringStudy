package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AllController {

	/*
	@GetMapping("/all/menu1")
	public void menu1() {
	}
	*/
	
	//이렇게 해도 위와 같음. /all/menu1이라는 뷰를 호출
	@GetMapping("/all/menu1")
	public String menu1() {
		return "/all/menu1";
	}
	
	
	@GetMapping("/all/menu2")
	public void menu2() {
	}
}
