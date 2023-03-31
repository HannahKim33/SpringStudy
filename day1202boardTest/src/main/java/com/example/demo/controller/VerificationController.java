package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.youiwe.webservice.BitSms;

@RestController
public class VerificationController {
	
	@Autowired
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	private BitSms sms;

	public void setSms(BitSms sms) {
		this.sms = sms;
	}


	@GetMapping("/sendVerifCode")
	public String verif(String to, String verifType) {
		String code="";
		Random r=new Random();
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		code+=r.nextInt(10);
		if(verifType.equals("email")) {
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setFrom("kgukgu33@gmail.com");
			mailMessage.setTo(to);
			mailMessage.setSubject("인증코드 전송");
			mailMessage.setText("인증코드: "+code);
			try {
				mailSender.send(mailMessage);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}else {
			sms.sendMsg("01025598279", to, "인증코드: "+code);
		}
		
		return code;
	}
}
