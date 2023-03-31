package com.example.demo.schedule;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@Component
@EnableScheduling
@Setter
public class EmailSchedule {
	
	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private JavaMailSender sender;
	
	/*
	@cron()
	public sendMailHtml() {
		
		List<EmpVO> list=dao.findAll();
		for(EmpVO e:list) {
			String str=SistUtil.getHtml(e);
			
			sender.send(new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					
					MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true, "UTF-8");
					helper.setFrom("kgukgu33@gmail.com");
					helper.setTo(e.getEmail());
					helper.setSubject("급여명세서");
					helper.setText(str,true);
				}
			});
		}
	}*/
}
