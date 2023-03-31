package com.example.demo.common;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class DAOCommon {
	/*
	@Pointcut("execution(public * com.example.demo.dao..*(..))")
	public void daoCommon() {}
	*/
	
	/*
	@Pointcut("execution(public * com.example.demo.controller..*(..))")
	public void controllerCommon() {}
	
	@Around("controllerCommon()")
	public void around(JoinPoint joinPoint) {
		
		
		Object obj=null;
		long start=System.currentTimeMillis();
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis();
		System.out.println("걸린시간:"+(end-start));
		
		
		Date today=new Date();
		
		//String ip=request.getRemoteAddr();
		//System.out.println(ip);
		
		System.out.println(today);
		System.out.println("--------------------------------");
	}
	*/
	/*
	public void afterReturning(JoinPoint joinPoint, Object ret) {
		System.out.println("--------------------------------");
		String methodName=joinPoint.getSignature().getName();
		System.out.println("타깃 메소드("+methodName+")가 정상 완료되었습니다.");
		System.out.println("반환값:"+ret);
		System.out.println("--------------------------------");
	}
	*/
	/*
	@AfterThrowing(pointcut="execution(public * com.example.demo.dao..*(..))", throwing ="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName=joinPoint.getSignature().getName();
		System.out.println("----------------------------------------------");
		System.out.println("타깃 메소드("+methodName+")가 비정상 완료되었습니다.");
		System.out.println("예외:"+ex.getMessage());
		System.out.println("----------------------------------------------");
	}
	*/
	/*
	public void afterAdvice(JoinPoint joinPoint) {
		String methodName=joinPoint.getSignature().getName();
		System.out.println("타깃 메소드("+methodName+")가 완료되었습니다.");
	}
	*/
	
	/*
	@AfterThrowing("daoCommon()")
	public void afterThrowing(JoinPoint joinPoint) {
		String methodName=joinPoint.getSignature().getName();
		System.out.println("타깃 메소드("+methodName+")가 비정상 완료되었습니다.");
	}
	*/
	/*
	@AfterReturning("daoCommon()")
	public void afterReturning(JoinPoint joinPoint) {
		String methodName=joinPoint.getSignature().getName();
		System.out.println("타깃 메소드("+methodName+")가 정상 완료되었습니다.");
	}
	*/
	/*
	@Around("daoCommon()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object obj=null;
		String methodName=joinPoint.getSignature().getName();
		System.out.println(methodName+"가 동작하기 전입니다.");
		long start=System.currentTimeMillis();
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis();
		System.out.println(methodName+"가 완료되었습니다.");
		System.out.println("걸린시간:"+(end-start));
		return obj;
	}
	*/
	
	/*
	@Before("daoCommon()")
	public void pro(JoinPoint joinpoint) {
		String methodName1=joinpoint.getSignature().toLongString();
		String methodName2=joinpoint.getSignature().toShortString();
		System.out.println(methodName1);
		System.out.println(methodName2);
		Object targetObject=joinpoint.getTarget();
		System.out.println("타겟객체"+targetObject);
		Object []args=joinpoint.getArgs();
		System.out.println("매개변수 목록");
		if(args!=null) {
			for(Object obj:args) {
				System.out.println(obj);
			}
		}
		
		System.out.println("DAO가 처리되기 전에 동작하는 공통 기능입니다.");
		System.out.println("-------------------------------");
	}
	*/
}
