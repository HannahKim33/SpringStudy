package com.example.demo.vo;

import lombok.Data;

@Data
public class EmpVO {
	private int eno;
	private String ename;
	private int dno;
	private int salary,comm;
	private String hiredate,phone,addr;
	private int mgr;
	private String job,email,jumin;
}
