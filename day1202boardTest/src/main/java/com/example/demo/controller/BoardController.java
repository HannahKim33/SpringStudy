package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

@Controller
public class BoardController {
	
	private static int pageSIZE=3;	//한 화면에 보여줄 레코드의 수
	private static int totalRecord=0; //전체 레코드(게시글)의 수
	private static int totalPage=0;	//전체 페이지 수
	
	private static int pageGroup=5; //한 화면에 보여줄 페이지 번호 수 (1 2 3 4 5 ▶ / ◀ 6 7 8 9 10 ▶ / ◀ 11 ...)
	
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/listBoard")
	public void list(Model model,
			@RequestParam(value="pageNUM", defaultValue="1") int pageNUM) {
		totalRecord=dao.getTotalRecord();
		totalPage=(int) Math.ceil((double)totalRecord/pageSIZE);
		
		int start=(pageNUM-1)*pageSIZE+1;
		int end=start+pageSIZE-1;
		HashMap<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		System.out.println("pageNUM:"+pageNUM);
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		
		model.addAttribute("list",dao.findAll(map));
		model.addAttribute("totalPage", totalPage);
		
		//만약 현재페이지가 1,2,3,4,5 ==> startPage=1, endPage=5
		//만약 현재페이지가 6,7,8,9,10 ==> startPage=6, endPage=10
		//만약 현재페이지가 11,12,13,14,15 ==> startPage=11, endPage=15
		//9를 5로 나눈 몫->1 1*5+1
		
		int startPage=((pageNUM-1)/pageGroup)*pageGroup+1;
		int endPage=startPage+pageGroup-1;
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		
		System.out.println("startPage:"+startPage);
		System.out.println("endPage:"+endPage);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}
	
	@GetMapping("/detailBoard")
	public void detail(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
}
