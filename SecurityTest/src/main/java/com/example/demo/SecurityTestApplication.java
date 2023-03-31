package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@SpringBootApplication
public class SecurityTestApplication {

	public static void main(String[] args) {
		
//		DBManager.insert(new MemberVO("tiger",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("tiger"),"홍길동","user"));
//		DBManager.insert(new MemberVO("master",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"),"김유신","admin"));
//		
		SpringApplication.run(SecurityTestApplication.class, args);
	}

}
