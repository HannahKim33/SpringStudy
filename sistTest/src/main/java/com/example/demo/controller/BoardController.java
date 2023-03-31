package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	@Autowired
	private BoardDAO dao;

	
	@GetMapping("/board/list")
	public void list(Model model) {
		model.addAttribute("list",dao.findAll());
	}
	
	@GetMapping("/board/insert")
	public void insertForm(@RequestParam(value="no", defaultValue="0") int no, Model model, javax.servlet.http.HttpSession session) {
		model.addAttribute("no",no);
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(BoardVO b, javax.servlet.http.HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("redirect:/listBoard");
		
		
		int p_no=b.getNo();
		BoardVO p=dao.findByNo(p_no);
		
		int no=dao.getNextNo();
		int b_level=0;
		int b_step=0;
		int b_ref=no;
		
		if(p_no!=0) {
			//답글
			//b_ref는 부모글의 b_ref와 동일하게 한다
			b_ref=p.getB_ref();
			
			//b_step과 b_level은 부모글보다 1씩 증가시킨다.
			b_level=p.getB_level();
			b_step=p.getB_step();
			
			//이미 달려 있는 답글들의 b_step을 1씩 증가시킨다.
			dao.increaseB_step(b_ref,b_step);
			
			//b_step과 b_level은 부모글보다 1씩 증가시킨다.
			b_level++;
			b_step++;
		}
		
		
		b.setNo(no);
		b.setB_level(b_level);
		b.setB_step(b_step);
		b.setB_ref(b_ref);
		
		String path=request.getServletContext().getRealPath("data");
		String fname=b.getUploadFile().getOriginalFilename();
		MultipartFile uploadFile=b.getUploadFile();
		
		if(fname!=null && !fname.equals("")) {
			try {
				byte[] data=uploadFile.getBytes();
				FileOutputStream fos=new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			fname="";
		}
		
		b.setFname(fname);
		
		int re=dao.insert(b);
		
		if(re<=0) {
			mav.addObject("msg", "게시물 등록 실패");
			mav.setViewName("error");
		}
		
		return mav;
	}

	
	@GetMapping("/board/update")
	public void updateForm(int no, Model model) {
		model.addAttribute("b",dao.findByNo(no));
	}
	
	@PostMapping("/board/update")
	public ModelAndView updateSubmit(BoardVO b, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("redirect:/listBoard");
		String path=request.getServletContext().getRealPath("data");
		String oldFname=b.getFname();
		String fname="";
		MultipartFile uploadFile=b.getUploadFile();
		fname=uploadFile.getOriginalFilename();
		if(fname!=null && !fname.equals("")) {
			b.setFname(fname);
		}
		int re=dao.update(b);
		
		if(re<=0) {
			mav.addObject("msg","게시물 수정에 실패했습니다.");
			mav.setViewName("error");
		}else {
			if(fname!=null && !fname.equals("")) {
				byte[] data;
				try {
					data = uploadFile.getBytes();
					FileOutputStream fos=new FileOutputStream(path+"/"+fname);
					fos.write(data);
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(oldFname!=null && !oldFname.equals("")) {
					File file=new File(path+"/"+oldFname);
					file.delete();
				}
			}
		}
		
		return mav;
	}

	
	@GetMapping("/board/delete")
	public void deleteForm(int no, Model model) {
		model.addAttribute("no", no);
	}
	
	@PostMapping("/board/delete")
	public ModelAndView deleteSubmit(int no, String pwd, HttpServletRequest request) {
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
