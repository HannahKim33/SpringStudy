package com.example.demo.controller;

import java.io.InputStream;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	@GetMapping("/listMember")
	public String list() {
		String r="";
		try {
			String addr="";
			URL url=new URL(addr);
			InputStream is = url.openStream();
			r=new String(is.readAllBytes());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return r;
	}
}
