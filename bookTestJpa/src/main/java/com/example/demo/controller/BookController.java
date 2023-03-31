package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.BookService;
import com.example.demo.vo.BookVO;

import lombok.Setter;

@Controller
@Setter
public class BookController {
	
	@Autowired
	private BookService bs;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav=new ModelAndView("/index");
		return mav;
	}
	
	@RequestMapping("/book/list")
	public void list(Model model, String keyword, String col, HttpServletRequest request) {
		if(request.getMethod().equals("GET")) {
			model.addAttribute("list",bs.findAll());
		}else {
			
			/*
			switch(col) {
			case "bookname":
				model.addAttribute("list",bs.findByBooknameContaining(keyword));break;
			case "bookid":
				model.addAttribute("list",bs.findByBookidGreaterThan(Integer.parseInt(keyword)));break;
			case "publisher":
				model.addAttribute("list",bs.findByPublisherContaining(keyword));break;
			case "price":
				model.addAttribute("list",bs.findByPriceGreaterThan(Integer.parseInt(keyword)));break;
			}*/
			
			model.addAttribute("list",bs.search(keyword,col));
		}
	}
	
	@GetMapping("/book/insert")
	public void insertForm() {
		
	}
	
	@PostMapping("/book/save")
	public ModelAndView save(BookVO b) {
		ModelAndView mav=new ModelAndView("redirect:/book/list");
		bs.save(b);
		return mav;
	}
	
	@GetMapping("/book/update/{bookid}")
	public ModelAndView update(@PathVariable int bookid) {
		ModelAndView mav=new ModelAndView("/book/update");
		mav.addObject("b", bs.getOne(bookid));
		return mav;
	}
	
	@GetMapping("/book/delete/{bookid}")
	public ModelAndView delete(@PathVariable int bookid) {
		ModelAndView mav=new ModelAndView("redirect:/book/list");
		bs.delete(bookid);
		return mav;
	}
}
