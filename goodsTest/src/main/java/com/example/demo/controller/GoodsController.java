package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class GoodsController {
	
	int pageSIZE=4;
	int totalRecord=0;
	int totalPage=1;
	
	@Autowired
	private GoodsDAO dao;

	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}

	/*
	@RequestMapping("/listGoods")
	public ModelAndView list(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("list",dao.findAll());
		return mav;
	}
	*/
	
	@GetMapping("/listGoods")
	public void list(
			Model model, 
			@RequestParam(value="pageNUM",defaultValue="1") int pageNUM,
			String orderColumn,
			HttpSession session,
			String keyword,
			String searchColumn,
			String op,
			String reset
			) {
		System.out.println("searchColumn:"+searchColumn);
	//HttpSession import 할 때 javax.servlet.http.HttpSession <- ver 2.7.0
	//	jakarta.servlet.http.HttpSession <-ver 3.0.0 <-이거 써야 함
	/*	
	 * ↓ 이것의 버전
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	*/
		if (reset!=null && reset.equals("yes")) {
			session.removeAttribute("searchcolumn");
			session.removeAttribute("keyword");
			session.removeAttribute("op");
		}
		
		String session_column=null;
		
		if(session.getAttribute("session_column")!=null) {
			session_column=(String)session.getAttribute("session_column");
		}
		if(orderColumn!=null && !orderColumn.equals("")) {
			session_column=orderColumn;
		}
		
		//언제 세션에 있는 검색어를 갖고 오느냐?
		//새로운 검색어가 없고, 세션에 저장된 키워드가 있을 때
		if(session.getAttribute("keyword")!=null && (keyword==null || keyword.equals(""))) {
			keyword=(String)session.getAttribute("keyword");
			searchColumn=(String)session.getAttribute("searchColumn");
			if(searchColumn!=null && !searchColumn.equals("name")) {
				op=(String)session.getAttribute("op");
			}
		}
		
		HashMap<String, Object> map=new HashMap<>();

		map.put("orderColumn", session_column);
		map.put("keyword", keyword);
		map.put("searchColumn", searchColumn);
		map.put("op", op);
		
		
		totalRecord=dao.getTotalRecord(map);
		totalPage=(int)Math.ceil((double)totalRecord/pageSIZE);
		
		int start=(pageNUM-1)*pageSIZE+1;
		int end=pageNUM*pageSIZE;
		if (end>totalRecord) {
			end=totalRecord;
		}
		
		map.put("start", start);
		map.put("end", end);
		
		System.out.println("pageNUM: "+pageNUM);
		System.out.println("orderColumn:"+orderColumn);
		System.out.println("totalPage: "+totalPage);
		System.out.println("keyword: "+keyword);
		System.out.println("searchColumn:"+searchColumn);
		System.out.println("------------------------------");
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", dao.findAll(map));
		
		session.setAttribute("session_column", session_column);
		session.setAttribute("keyword", keyword);
		session.setAttribute("searchColumn", searchColumn);
		session.setAttribute("op", op);

	}
	
	@GetMapping("/insertGoods")
	public void insertForm() {
		
	}
	@PostMapping("/insertGoods")
	public ModelAndView insert(GoodsVO g, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("redirect:/listGoods");
		
		MultipartFile uploadFile=g.getUploadFile();
		String fname=uploadFile.getOriginalFilename();
		
		String path=request.getServletContext().getRealPath("images");
		System.out.println("path: "+path);
		
		try {
			byte[] data=uploadFile.getBytes();
			FileOutputStream fos=new FileOutputStream(path+"/"+fname);
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setFname(fname);
		
		int re=dao.insert(g);
		if(re<=0) {
			mav.addObject("msg", "등록 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	
	/*
	@RequestMapping("/detailGoods")
	public ModelAndView detail(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("g", dao.findByNo(no));
		return mav;
	}
	*/  //↓똑같음 
	
	@GetMapping("/detailGoods")
	public void detail(Model model, int no) {
		model.addAttribute("g", dao.findByNo(no));
	}
	
	/*
	@GetMapping("/updateGoods")
	public ModelAndView updateForm(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("g", dao.findByNo(no));
		return mav;
	}
	
	@PostMapping("/updateGoods")
	public ModelAndView update(GoodsVO g) {
		ModelAndView mav=new ModelAndView("redirect:/listGoods");
		int re=dao.update(g);
		if(re<=0) {
			mav.addObject("msg", "등록 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	*/
	
	@RequestMapping("/deleteGoods")
	public ModelAndView delete(int no, HttpServletRequest request ) {
		ModelAndView mav=new ModelAndView("redirect:/listGoods");
		String fname=dao.findByNo(no).getFname();
		String path=request.getServletContext().getRealPath("images");
		
		int re=dao.delete(no);
		if(re>0) {
			File file=new File(path+"/"+fname);
			file.delete();
		}else{
			mav.addObject("msg","상품 삭제 실패");
			mav.setViewName("error");
		}
		return mav;
	}
}
