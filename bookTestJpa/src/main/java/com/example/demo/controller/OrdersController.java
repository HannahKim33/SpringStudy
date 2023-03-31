package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.BookService;
import com.example.demo.CustomerService;
import com.example.demo.OrdersService;
import com.example.demo.dao.View_ListOrdersDAO;
import com.example.demo.dao.View_ListOrdersDAO2;
import com.example.demo.vo.BookVO;
import com.example.demo.vo.CustomerVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.View_ListOrders;

import lombok.Setter;

@Controller
@Setter
public class OrdersController {
	@Autowired
	private OrdersService os;
	@Autowired
	private BookService bs;
	@Autowired
	private CustomerService cs;
	@Autowired
	private View_ListOrdersDAO view_ListOrdersDAO;
	@Autowired
	private View_ListOrdersDAO2 view_ListOrdersDAO2;
	
	@GetMapping("/orders/insert")
	public void insertForm(Model model) {
		model.addAttribute("orderid", os.getNextNo());
		System.out.println("nextno:"+os.getNextNo());
		model.addAttribute("c_list", cs.findAll());
		model.addAttribute("b_list", bs.findAll());
	}
	
	@PostMapping("/orders/insert")
	public ModelAndView insertSubmit(OrdersVO o) {
		System.out.println("***************ordersVO:"+o);
		os.insert(o);
		ModelAndView mav=new ModelAndView("redirect:/orders/list");
		return mav;
	}
	
	@RequestMapping("/orders/list2")
	public void list(Model model) {
		model.addAttribute("list",view_ListOrdersDAO.findAll());
	}
	
	@RequestMapping("/orders/list2/{col}")
	public ModelAndView list2( String keyword, @PathVariable String col) {
		ModelAndView mav=new ModelAndView("/orders/list2");
		List<View_ListOrders> list=null;
		System.out.println("*************************col:"+col);
		
		switch(col) {
		case "orderid":list=view_ListOrdersDAO.findAllOrderByOrderid(); break;
		case "name":list=view_ListOrdersDAO.findAllOrderByName(); break;
		case "bookname":list=view_ListOrdersDAO.findAllOrderByBookname(); break;
		case "orderdate":list=view_ListOrdersDAO.findAllOrderByOrderdate(); break;
		case "saleprice":list=view_ListOrdersDAO.findAllOrderBySaleprice(); break;
		case "price":list=view_ListOrdersDAO.findAllOrderByPrice(); break;
		}
		/*
		if(keyword==null || keyword.equals("")) {
			list=view_ListOrdersDAO.findAll();
		}else {
			list=view_ListOrdersDAO.findByNameContaining(keyword);
		}
		*/
		
		mav.addObject("list",list);
		return mav;
	}
	
	@GetMapping("/orders/list3")
	public void list3(Model model) {
		model.addAttribute("list", view_ListOrdersDAO2.findAll());
	}
	
}
