package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import com.example.demo.vo.DeptVO;


public class DBManager {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			System.out.println("예외발생 "+e.getMessage());
		}
	}
	public static List<DeptVO> findAll(){
		
	}
}
