package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.DeptDAO;

import lombok.Setter;

@Controller
@Setter
public class DeptController {
	
	@Autowired
	private DeptDAO dao;
	
	@RequestMapping("/dept/list")
	public void list(Model model) {
		model.addAttribute("list",dao.findAll());
	}
	
	@GetMapping("/dept/detail/{dno}")
	public ModelAndView detail(@PathVariable int dno) {
		ModelAndView mav=new ModelAndView("/dept/detail");
		System.out.println("부서번호:"+dno);
		mav.addObject("d",dao.findById(dno));
		return mav;
	}
	
	@GetMapping("/dept/delete/{dno}")
	public ModelAndView delete(@PathVariable int dno) {
		ModelAndView mav=new ModelAndView();
		System.out.println("부서번호:"+dno);
		int re=dao.delete(dno);
		System.out.println("re:"+re);
		if(re>0) {
			mav.setViewName("redirect:/dept/list");
		}else {
			mav.setViewName("error");
		}
		return mav;
	}
}
