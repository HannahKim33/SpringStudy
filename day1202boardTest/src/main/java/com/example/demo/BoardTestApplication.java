package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling	//스케줄링을 enable 하기
public class BoardTestApplication {

	/*
	@Scheduled(fixedRate=10000)		//10초마다 이 메소드가 동작하게 하는 어노테이션
	public void pro() {
		System.out.println("콜콜");
	}
	*/
	public static void main(String[] args) {
		SpringApplication.run(BoardTestApplication.class, args);
	}
}
