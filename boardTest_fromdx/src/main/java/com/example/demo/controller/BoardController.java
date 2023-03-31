package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.BoardDAO;

@Controller
public class BoardController {
	
	public int pageSIZE =  3;	// 한 화면에 보여줄 레코드의 수
	public int totalRecord = 0;
	public int totalPage = 1;
	
	public int pageGROUP  = 5;	// 한 화면에 보여줄 페이지 수 

	@Autowired
	private BoardDAO dao;	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	@GetMapping("/listBoard")
	public void list(Model model,
			@RequestParam(value = "pageNUM", defaultValue = "1")  int pageNUM) {
		totalRecord = dao.getTotalRecord();
		totalPage = (int) Math.ceil(totalRecord / (double)pageSIZE);
		int start = (pageNUM-1)*pageSIZE + 1;
		int end = start +  pageSIZE - 1;
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", dao.findAll(map));
		
		//만약, 현재페이지가 1,2,3,4,5 	===> 1, 5 
		//만약, 현재페이지가 6,7,8,9,10 ===> 6, 10
		int startPage = (pageNUM-1)/pageGROUP*pageGROUP+1;
		int endPage = startPage+pageGROUP-1;
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		
	}
	
	
	@GetMapping("/detailBoard")
	public void detail(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
}



















