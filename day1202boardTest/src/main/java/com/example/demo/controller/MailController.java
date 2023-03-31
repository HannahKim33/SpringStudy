package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
	
	@Autowired
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@RequestMapping("/mail")
	@ResponseBody
	public String mail(String code) {
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom("kgukgu33@gmail.com");
		mailMessage.setTo("kgukgu33@gmail.com");
		mailMessage.setSubject("이메일 인증 코드");
		mailMessage.setText("인증 코드: "+code);
		
		try {
			mailSender.send(mailMessage);
		}catch(Exception e) {
			e.getStackTrace();
			System.out.println("예외발생: "+e.getMessage());
		}
		return "OK";
	}
	
	@RequestMapping("/sendCode")
	@ResponseBody
	public String sendCode(String email) {
		System.out.println("email:"+email);
		String code="";
		Random r=new Random();
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom("kgukgu33@gmail.com");
		mailMessage.setTo(email);
		mailMessage.setSubject("이메일 인증 코드");
		mailMessage.setText("인증 코드: "+code);
		
		try {
			mailSender.send(mailMessage);
		}catch(Exception e) {
			e.getStackTrace();
			System.out.println("예외발생: "+e.getMessage());
		}
		
		return code;
	}
}
