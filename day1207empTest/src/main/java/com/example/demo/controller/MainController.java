package com.example.demo.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.EmpDAO;
import com.example.demo.schedule.SistUtil;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@RestController
@Setter
public class MainController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmpDAO dao;
	

	@RequestMapping("/index")
	public ModelAndView main() {
		ModelAndView mav=new ModelAndView();
		return mav;
	}
}
