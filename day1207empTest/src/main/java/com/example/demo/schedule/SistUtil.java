package com.example.demo.schedule;

import java.util.Date;

import com.example.demo.vo.EmpVO;

public class SistUtil {
	public static String getHtml(EmpVO e) {
		Date today=new Date();
		int year=today.getYear()+1900;
		int month=today.getMonth()+1;
		int totalSalary=e.getSalary()+e.getComm();
		String str="<h2>급여 명세서</h2>";
		str+=e.getEname()+"님의 급여 명세서";
		str+="<table border='1'>";
		str+="<tr>";
		str+="<td>기본급</td>";
		str+="<td>"+e.getSalary()+"</td>";
		str+="</tr>";
		str+="<tr>";
		str+="<td>성과급</td>";
		str+="<td>"+e.getComm()+"</td>";
		str+="<tr>";
		str+="<td>총합</td>";
		str+="<td>"+totalSalary+"</td>";
		str+="</tr>";
		str+="</table>";
		
		return str;
	}
}
