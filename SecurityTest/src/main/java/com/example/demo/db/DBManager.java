package com.example.demo.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVO;

public class DBManager {
	public static SqlSessionFactory sqlSessionFactory;
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
	
	public static int insert(MemberVO m) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.insert("member.insert",m);
		session.close();
		return re;
	}
	
	public static MemberVO findById(String id) {
		MemberVO m=null;
		SqlSession session=sqlSessionFactory.openSession(true);
		m=session.selectOne("member.findById",id);
		session.close();
		return m;
	}
}
