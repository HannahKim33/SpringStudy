package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private MemberDAO mdao;
	
	@GetMapping("/board/insert")
	public ModelAndView insertForm(Model model,@RequestParam (defaultValue="0") int p_no, HttpSession session) {
		ModelAndView mav=new ModelAndView();
		if(session.getAttribute("id")==null) {
			mav.setViewName("/member/login");
		}else {
			model.addAttribute("no",bs.getNextNo());
			model.addAttribute("p_no",p_no);
			System.out.println("p_no:"+p_no);
			
			String id=(String) session.getAttribute("id");
			System.out.println("name:"+mdao.findById(id).get().getName());
			mav.addObject("name",mdao.findById(id).get().getName());
		}
		return mav;
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(Board b, int p_no, HttpServletRequest request, HttpSession session) {
		ModelAndView mav=new ModelAndView("redirect:/board/list/1/all");
		
		System.out.println("p_no::"+p_no);
		int b_ref=b.getNo();
		int b_step=0;
		int b_level=0;
		
		if(p_no!=0) {
			Board p=bs.findByNo(p_no);
			b_ref=p.getB_ref();
			b_step=p.getB_step();
			b_level=p.getB_level();
			bs.updateStep(b_ref, b_step);
			b_step++;
			b_level++;
		}
		
		b.setB_ref(b_ref);
		b.setB_step(b_step);
		b.setB_level(b_level);
		
		//파일
		
		MultipartFile uploadFile=b.getUploadFile();
		String fname=uploadFile.getOriginalFilename();
		
		String path=request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		System.out.println("***********************fname:"+fname);
		
		if(fname!=null && !fname.equals("")) {
			try {
//				byte[] data=uploadFile.getBytes();
//				FileOutputStream fos=new FileOutputStream(path+"/"+fname);
//				fos.write(data);
//				fos.close();
				FileOutputStream fos=new FileOutputStream(path+"/"+fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		b.setFname(fname);
		
		b.setIp(request.getRemoteAddr());
		bs.insert(b);
		
		return mav;
	}
	
	@GetMapping("/board/list/{i}/{id}")
	public ModelAndView list(@PathVariable int i, @PathVariable String id) {
		ModelAndView mav=new ModelAndView("/board/list");
		
		
		int pageSize=5;
		int totalCount=0;
		
		if(id.equals("all")) {
			totalCount=bs.count();
		}else {
			totalCount=bs.countById(id);
		}
		int totalPage=(int)Math.ceil((double)totalCount/pageSize);
		
		int start=(i-1)*pageSize+1;
		int end=start+pageSize-1;
		
		
		if(id.equals("all")) {
			mav.addObject("list", bs.findAllOrderBy(start,end));
		}else {
			mav.addObject("list",bs.selectAllById(start, end, id));
		}
		mav.addObject("totalPage",totalPage);
		System.out.println("pageNum:"+i);
		System.out.println("id:"+id);
		return mav;
	}
	
	@GetMapping("/board/detail/{no}")
	public ModelAndView detail(@PathVariable int no) {
		ModelAndView mav=new ModelAndView("/board/detail");
		mav.addObject("b",bs.findByNo(no));
		return mav;
	}
	
	@GetMapping("/board/delete/{no}")
	public ModelAndView deleteForm(@PathVariable int no, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("/board/delete");
		mav.addObject("no",no);
		return mav;
	}
	
	@PostMapping("/board/delete")
	public ModelAndView deleteSubmit(int no, String pwd, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("redirect:/board/list/1/all");
		String path=request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		String fname=bs.findByNo(no).getFname();
		int re=bs.delete(no,pwd);
		if(re==1 && fname!=null && !fname.equals("")) {
			File file= new File(path+"/"+fname);
			file.delete();
		}
		return mav;
	}
	
	@GetMapping("/board/update/{no}")
	public ModelAndView updateForm(@PathVariable int no, HttpSession session) {
		ModelAndView mav=new ModelAndView("/board/update");
		if(session.getAttribute("id")==null) {
			mav.setViewName("/member/login");
		}else {
			mav.addObject("b",bs.findByNo(no));
		}
		return mav;
	}
	
	@PostMapping("/board/update")
	public ModelAndView updateSubmit(Board b, HttpServletRequest request, String oldFname) {
		ModelAndView mav=new ModelAndView("redirect:/board/list/1/all");
		
		String path=request.getServletContext().getRealPath("images");

		MultipartFile uploadFile=b.getUploadFile();
		String fname=uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
				b.setFname(fname);
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
		int re = bs.update(b);
		if(re > 0) {
			if(fname != null && !fname.equals("") && oldFname != null && !oldFname.equals("")) {
				try {
					File file = new File(path + "/" + oldFname);
					file.delete();
				}catch (Exception e) {
					System.out.println("예외발생:"+e.getMessage());
				}
			}
		}else {
			mav.addObject("msg", "게시물 수정에 실패하였습니다.");
			mav.setViewName("/error");
		}
		
		/*//왜 안될까...??
		System.out.println("oldFname:"+oldFname);
		System.out.println("fname:"+fname);
		
		if(fname==null || fname.equals("")) {
			b.setFname(oldFname);
		}else {
			b.setFname(fname);
		}
		
		
		int re=bs.update(b);
		System.out.println("re:"+re);
		
		if(re==1) {
			if(fname!=null && !fname.equals("")) {
				try {
					FileOutputStream fos=new FileOutputStream(path+"/"+fname);
					FileCopyUtils.copy(uploadFile.getBytes(), fos);
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(oldFname!=null && !oldFname.equals("")) {
					File file=new File(path+"/"+oldFname);
					file.delete();
				}
			}
		}
*/
		
		return mav;
	}
}
