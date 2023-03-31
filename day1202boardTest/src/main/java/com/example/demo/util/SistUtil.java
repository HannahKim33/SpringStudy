package com.example.demo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

@Component
@EnableScheduling
public class SistUtil {

	/*
	//@Scheduled(fixedRate=1000)
	@Scheduled(cron="0 24 11 * * ?")
	public void pro() {
		System.out.println("콜콜");
	}
	*/
	
	
	@Autowired
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	
	@Autowired
	private EmpDAO dao;
	
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}

	
	//급여명세서를 이메일로 보내는 메소드

	@Scheduled(cron="0 0 12 7 * ?")
	public void sendPayslip() {
		List<EmpVO> list=dao.findAll();
		System.out.println("list: "+list);
		for(EmpVO e:list) {
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setFrom("kgukgu33@gmail.com");
			mailMessage.setTo(e.getEmail());
			mailMessage.setSubject("급여명세서");
			mailMessage.setText(e.getEname()+"님의 급여: "+e.getSalary());
			try {
				mailSender.send(mailMessage);
				System.out.println(e.getEname()+"님에게 급여명세서 발송 완료");
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
