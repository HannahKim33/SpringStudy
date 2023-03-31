package com.example.demo.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DeptVO;
import com.example.demo.vo.LogVO;

public class DBManager {
	
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "com/example/demo/db/sqlMapConfig.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
					new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<DeptVO> findAllDept() {
		List<DeptVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("dept.findAll");
		session.close();
		return list;
	}
	
	public static int insertDept(DeptVO d) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.insert("dept.insert",d);
		session.close();
		return re;
	}

	public static int updateDept(DeptVO d) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.update("dept.update",d);
		session.close();
		return re;
	}
	
	public static int deleteDept(int dno) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.delete("dept.delete",dno);
		session.close();
		return re;
	}

	public static int insertLog(LogVO lvo) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.insert("dept.insertLog",lvo);
		session.close();
		return re;
	}
	
}
