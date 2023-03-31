package com.example.demo.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.GoodsVO;

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
	public static List<GoodsVO> findAll(HashMap<String, Object> map){
		List<GoodsVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("goods.findAll",map);
		session.close();
		return list;
	}
	public static int insert(GoodsVO g) {
		SqlSession session=sqlSessionFactory.openSession(true);
		int re=session.insert("goods.insert",g);
		session.close();
		return re;
	}
	public static GoodsVO findByNo(int no) {
		GoodsVO g=null;
		SqlSession session=sqlSessionFactory.openSession();
		g=session.selectOne("goods.findByNo", no);
		session.close();
		return g;
	}
	public static int update(GoodsVO g) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.update("goods.update", g);
		session.close();
		return re;
	}
	public static int delete(int no) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.delete("goods.delete", no);
		session.close();
		return re;
	}
	
	public static int getTotal(HashMap<String, Object> map) {
		System.out.println("DBManager에서 map: "+map);
		int cnt=0;
		SqlSession session=sqlSessionFactory.openSession();
		cnt=session.selectOne("goods.total", map);
		session.close();
		return cnt;
	}
}
