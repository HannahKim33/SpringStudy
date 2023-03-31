package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerDAO dao;

	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/listCustomer")	//setViewName을 하지 않으면 자동으로 listCustomer.jsp로 연결됨
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("list", dao.findAll());
		return mav;
	}
	
	@RequestMapping("/detailCustomer")
	public ModelAndView detail(int custid) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("c", dao.findByCustid(custid));
		return mav;
	}
	
	//@RequestMapping(value="/insertCustomer", method=RequestMethod.GET)
	@GetMapping("/insertCustomer")
	public void insertForm() {
		
	}
	
	//@RequestMapping(value="/insertCustomer", method=RequestMethod.POST)
	@PostMapping("/insertCustomer")
	public ModelAndView insertSubmit(CustomerVO c) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("re",dao.insert(c));
		return mav;
	}
	
	@GetMapping("/updateCustomer")
	public ModelAndView updateForm(int custid) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("c",dao.findByCustid(custid));
		return mav;
	}
	
	@PostMapping("/updateCustomer")
	public ModelAndView updateSubmit(CustomerVO c) {
		ModelAndView mav=new ModelAndView();
		int re=dao.update(c);
		if(re>0) {
			mav.setViewName("redirect:/listCustomer");
		}
		return mav;
	}
	
	@RequestMapping("/deleteCustomer")
	public ModelAndView deleteSubmit(int custid) {
		ModelAndView mav=new ModelAndView("redirect:/listCustomer"); //mav를 생성할 때 viewname을 set 할 수 있음
		int re=dao.delete(custid);
		if(re<=0) {
			mav.addObject("msg","고객 삭제에 실패했습니다.");
			mav.setViewName("error");
		}
		return mav; 
	}
}