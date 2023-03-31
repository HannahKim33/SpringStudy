package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/deleteBoard")
public class DeleteBoardController {
	
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping
	public void form(int no, Model model) {
		model.addAttribute("no", no);
	}
	
	@PostMapping
	public ModelAndView submit(int no, String pwd, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("redirect:/listBoard");
		String path=request.getServletContext().getRealPath("data");
		String fname=dao.findByNo(no).getFname();
		
		HashMap<String, Object> map=new HashMap<>();
		map.put("no", no);
		map.put("pwd", pwd);
	
		int re=dao.delete(map);
		if(re<=0) {
			mav.addObject("msg", "삭제 실패");
			mav.setViewName("error");
		}else {
			File file=new File(path+"/"+fname);
			file.delete();
		}
		return mav;
	}
	
}
