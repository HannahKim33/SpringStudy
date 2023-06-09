package com.example.jpatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpatest.GoodsService;
import com.example.jpatest.dao.GoodsDAO;
import com.example.jpatest.vo.GoodsVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsService gs;

	@GetMapping("/goods/list")
	public void list(Model model) {
		model.addAttribute("list",gs.findAll());
	}
	
	@GetMapping("/goods/insert")
	public void insert() {
		
	}
	
	@PostMapping("/goods/save")
	public ModelAndView save(GoodsVO g) {
		ModelAndView mav=new ModelAndView("redirect:/goods/list");
		gs.save(g);
		return mav;
	}
	
	@GetMapping("/goods/update/{no}")
	public ModelAndView update(@PathVariable int no) {
		ModelAndView mav=new ModelAndView("/goods/update");
		mav.addObject("g",gs.findById(no));
		return mav;
	}
	
	@GetMapping("/goods/delete/{no}")
	public ModelAndView delete(@PathVariable int no) {
		ModelAndView mav=new ModelAndView("redirect:/goods/list");
		gs.delete(no);
		return mav;
	}
}
