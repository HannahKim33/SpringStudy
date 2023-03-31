package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController {
	
	@GetMapping("/sum/{n}")
	public String sumTest(@PathVariable int n) {
		int r=0;
		r=BitSum.getSum(n);
		String result=""+r;
		return result;
	}
}
