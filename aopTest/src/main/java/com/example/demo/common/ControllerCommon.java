package com.example.demo.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.LogDAO;
import com.example.demo.vo.LogVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Component
@Aspect
@Setter
public class ControllerCommon {
	
	@Autowired
	private LogDAO dao;
	
	@Pointcut("execution(public * com.example.demo.controller..*(..))")
	public void controllerCommon() {}
	
	
	@Around("controllerCommon()")
	public Object pro(ProceedingJoinPoint joinPoint) {
		//타깃메소드의 매개변수들을 배열로 가져온다.
		Object []args=joinPoint.getArgs();
		
		//매개변수의 첫번째 요소를 갖고 오면 그것이 request
		HttpServletRequest request=(HttpServletRequest) args[0];
		
		String ip=request.getRemoteAddr();
		String uri=request.getRequestURI();
		
		Date today=new Date();
		int year=today.getYear()+1900;
		int month=today.getMonth()+1;
		int date=today.getDate();
		int hour=today.getHours();
		int minute=today.getMinutes();
		int seconds=today.getSeconds();
		
		String time="";
		if(date<10) {
			time=year+"-"+month+"-0"+date+" "+hour+":"+minute+":"+seconds;
		}else {
			time=year+"-"+month+"-"+date+" "+hour+":"+minute+":"+seconds;
		}

		
		long start=System.currentTimeMillis();
		
		Object ret=null;
		try {
			ret = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis();
		long delay=end-start;
		
		System.out.println(uri+"/"+ip+"/"+delay+"/"+time);
		
		/*
		LogVO lvo=new LogVO(uri,ip,delay,time);
		
		int re=dao.insertLog(lvo);
		*/
		
		/*//파일에 로그 저장하기
		FileWriter fw;
		try {
			fw = new FileWriter("c:/sist/log.txt",true);
			fw.write(uri+"/"+ip+"/"+delay+"/"+today+"\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return ret;
	}
}
