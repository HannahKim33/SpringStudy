package com.example.demo.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CustomerVO;

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
	
	public static List<CustomerVO> findAll(){
		List<CustomerVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("customer.findAll");
		session.close();
		return list;
	}
	
	public static CustomerVO findByCustid(int custid) {
		CustomerVO c=new CustomerVO();
		SqlSession session=sqlSessionFactory.openSession();
		c=session.selectOne("customer.findByCustid",custid);
		session.close();
		return c;
	}
	
	public static int insert(CustomerVO c) {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.insert("customer.insert",c);
		session.commit();
		session.close();
		return re;
	}

	public static int update(CustomerVO c) {
		SqlSession session=sqlSessionFactory.openSession(true);
										//autocommit true=>따로 session.commit 안 해도 됨
		int re=session.insert("customer.update", c);
		session.close();
		return re;
	}

	public static int delete(int custid) {
		SqlSession session=sqlSessionFactory.openSession(true);
		int re=session.delete("customer.delete",custid);
		session.close();
		return re;
	}
}
