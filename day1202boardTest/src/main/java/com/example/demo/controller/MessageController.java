package com.example.demo.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.youiwe.webservice.BitSms;

@RestController
public class MessageController {
	
	@GetMapping("/sendMsg")
	public String sendMsg() {
		String from="01025598279";
		String to="01020394356";
		String msg="테스트 메세지";
		
		BitSms sms=new BitSms();
		sms.sendMsg(from, to, msg);
		
		return "OK";
	}
	
	@RequestMapping("/sendCode_ph")
	public String sendCode(String phone) {
		String code="";
		Random r=new Random();
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		
		String from="01025598279";
		String to=phone;
		String msg="인증번호: "+code;
		
		BitSms sms=new BitSms();
		sms.sendMsg(from, to, msg);
		
		return code;
	}
}
