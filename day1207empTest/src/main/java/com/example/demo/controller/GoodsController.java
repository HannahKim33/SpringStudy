package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@RestController
@Setter
public class GoodsController {
	
	@Autowired
	private GoodsDAO dao;
	
	@RequestMapping("/listGoods")
	public List<GoodsVO> listGoods(){
		return dao.findAll();
	}
	
	@RequestMapping("/detailGoods")
	public GoodsVO detailGoods(int no){
		return dao.findByNo(no);
	}
	
	@RequestMapping("/insertGoods")
	public int insertGoods(GoodsVO g, HttpServletRequest request){
		String path=request.getServletContext().getRealPath("images");
		System.out.println("path: "+path);
		String fname="";
		
		
		MultipartFile uploadFile=g.getUploadFile();
		if(uploadFile!=null) {
			fname=uploadFile.getOriginalFilename();
		}
		System.out.println("fname: "+fname);
		

		
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
		g.setFname(fname);
		return dao.insert(g);
	}

	
	@RequestMapping("/updateGoods")
	public int updateGoods(GoodsVO g, HttpServletRequest request){
		String path=request.getServletContext().getRealPath("images");
		MultipartFile uploadFile=g.getUploadFile();
		String fname=uploadFile.getOriginalFilename();
		String oldFname=g.getFname();
		System.out.println("oldFname:"+oldFname);
		
		if(fname==null || fname.equals("")) {
			System.out.println("사진 수정 안함");
		}else {
			System.out.println("사진 수정함");
			g.setFname(fname);
		}
		
		int re=dao.update(g);
		
		if(re>0 && fname!=null && !fname.equals("")) {
			File file = new File(path+"/"+oldFname);
			file.delete();
			
			//수정에 성공하면 파일 업로드
			if(fname!=null && !fname.equals("")) {
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
			}
		}
		
		
		return re;
	}
	
	@RequestMapping("/deleteGoods")
	public int deleteGoods(int no,String fname,HttpServletRequest request) {
		int re=-1;
		String path=request.getServletContext().getRealPath("images");
		
		re=dao.delete(no);
		
		if(re>0 && !fname.equals("")) {
			File file=new File(path+"/"+fname);
			file.delete();
		}
		
		return re;
	}
}
