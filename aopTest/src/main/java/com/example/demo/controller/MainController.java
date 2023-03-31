package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.Setter;

@RestController
@Setter
public class MainController {


	@RequestMapping("/index")
	public ModelAndView main() {
		ModelAndView mav=new ModelAndView();
		return mav;
	}
}
