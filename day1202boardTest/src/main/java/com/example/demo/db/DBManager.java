package com.example.demo.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.MemberVO;

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
	
	public static List<BoardVO> findAll(HashMap<String, Object> map) {
		List<BoardVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("board.findAll",map);
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
	
	public static int insertMember(MemberVO m) {
		int re=-1;
		SqlSession session=sqlSessionFactory.openSession(true);
		re=session.insert("member.insert",m);
		session.close();
		return re;
	}
	
	public static boolean isMember(HashMap<String, Object> map) {
		boolean re=false;
		SqlSession session=sqlSessionFactory.openSession();
		MemberVO m=session.selectOne("member.isMember",map);
		if(m!=null) {
			re=true;
		}
		session.close();
		return re;
	}
	
	public static MemberVO findById(String id) {
		MemberVO m=null;
		SqlSession session=sqlSessionFactory.openSession();
		m=session.selectOne("member.findById",id);
		session.close();
		return m;
	}

	public static List<EmpVO> findAllEmp() {
		List<EmpVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("emp.findAll");
		return list;
	}
}
