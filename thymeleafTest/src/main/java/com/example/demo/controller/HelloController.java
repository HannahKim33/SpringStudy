package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.DeptDAO;

import lombok.Setter;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public void hello(Model model) {
		String name="홍길동";
		
		ArrayList<String> list=new ArrayList<String>();
		list.add("사과");
		list.add("포도");
		list.add("체리");
		list.add("토마토");
		
		model.addAttribute("name",name);
		model.addAttribute("list",list);
	}

}
