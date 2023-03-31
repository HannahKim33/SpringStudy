package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.MemberVO;

@Controller
public class MemberController {
	
	@RequestMapping("/list.do")
	@ResponseBody
	public List<MemberVO> list(){
		List<MemberVO> list=new ArrayList<MemberVO>();
		list.add(new MemberVO("이순신",20,"서울"));
		list.add(new MemberVO("김유신",30,"인천"));
		list.add(new MemberVO("홍길동",28,"광주"));
		return list;
	}
	
	
	@RequestMapping("/member.do")
	@ResponseBody
	public MemberVO member() {
		MemberVO b=new MemberVO("홍길동",20,"서울");
		return b;
	}
}
