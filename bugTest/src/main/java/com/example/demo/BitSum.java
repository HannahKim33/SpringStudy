package com.example.demo;

public class BitSum {
	public static int getSum(int n) {
		int tot=0;
		int i=0;
		for(i=1;i<n;i++) {
			tot+=i;
		}
		return tot;
	}
}
