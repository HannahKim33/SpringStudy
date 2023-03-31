package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
		List<DeptVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("dept.findAll");
		return list;
	}
	
	public static DeptVO findById(int dno){
		DeptVO d=null;
		SqlSession session=sqlSessionFactory.openSession();
		d=session.selectOne("findById",dno);
		return d;
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
}
