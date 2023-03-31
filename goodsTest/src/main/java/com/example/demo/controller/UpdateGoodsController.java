package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/updateGoods")
public class UpdateGoodsController {
	
	@Autowired
	private GoodsDAO dao;
	
	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(method=RequestMethod.GET)
	public void form(Model model, int no) {
		model.addAttribute("g", dao.findByNo(no));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView submit(GoodsVO g, HttpServletRequest request) {
		String path=request.getServletContext().getRealPath("images");
		ModelAndView mav=new ModelAndView("redirect:/listGoods");
		MultipartFile uploadFile=g.getUploadFile();
		String fname=uploadFile.getOriginalFilename();
		String oldFname=g.getFname();
		
		if(fname==null || fname.equals("")) {
			System.out.println("사진 수정 안함");
		}else {
			System.out.println("사진 수정함");
			
			byte[] data;
			try {
				data = uploadFile.getBytes();
				FileOutputStream fos=new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setFname(fname);
		}
		
		if(dao.update(g)<=0) {
			mav.addObject("msg", "상품 수정 실패");
			mav.setViewName("error");
		}else{
			if(fname!=null && !fname.equals("")) {
				File file = new File(path+"/"+oldFname);
				file.delete();
			}
		}
		return mav;
	}
}
