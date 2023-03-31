package com.example.demo.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.GoodsVO;
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
	

	public static List<GoodsVO> findAllGoods(){
		List<GoodsVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("goods.findAll");
		session.close();
		return list;
	}
	
	public static MemberVO findById(String id) {
		MemberVO m=null;
		SqlSession session=sqlSessionFactory.openSession(true);
		m=session.selectOne("member.findById",id);
		session.close();
		return m;
	}
	
	public static GoodsVO findGoodsByNo(int no){
		GoodsVO g=null;
		SqlSession session=sqlSessionFactory.openSession();
		g=session.selectOne("goods.findByNo",no);
		session.close();
		return g;
	}
	
	public static int insert(GoodsVO g) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.insert("goods.insert",g);
		session.close();
		return re;
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
	

	public static List<BoardVO> findAll() {
		List<BoardVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("board.findAll");
		session.close();
		return list;
	}
	
	public static int getTotalRecord() {
		int count=-1;
		SqlSession session=sqlSessionFactory.openSession();
		count=session.selectOne("board.getTotalRecord");
		session.close();
		return count;
	}
	
	public static BoardVO findByNo(int no) {
		BoardVO b=null;
		SqlSession session=sqlSessionFactory.openSession();
		b=session.selectOne("board.findByNo", no);
		session.close();
		return b;
	}
	
	public static int getNextNo() {
		int no=0;
		SqlSession session=sqlSessionFactory.openSession(true);
		no=session.selectOne("board.getNextNo");
		session.close();
		return no;
	}
	
	public static int insert(BoardVO b) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.insert("board.insert",b);
		session.close();
		return re;
	}

	public static int update(BoardVO b) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.update("board.update",b);
		session.close();
		return re;
	}

	public static int delete(HashMap<String, Object> map) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.delete("board.delete",map);
		session.close();
		return re;
	}
	
	public static void increaseB_step(int b_ref, int b_step) {
		HashMap<String, Object> map=new HashMap<>();
		map.put("b_ref", b_ref);
		map.put("b_step", b_step);
		SqlSession session=sqlSessionFactory.openSession(true);
		session.update("board.increaseB_step",map);
		session.close();
	}
}
