package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BoardVO;

@Repository
public class BoardDAO {
	
	public int getTotalRecord(){
		return DBManager.getTotalRecord();
	}
	
	public List<BoardVO> findAll(HashMap<String, Object> map){
		return DBManager.findAll(map);
	}
	
	public int getNextNo() {
		return DBManager.getNextNo();
	}
	
	public int insert(BoardVO b) {
		return DBManager.insert(b);
	}
	
	public BoardVO findByNo(int no) {
		return DBManager.findByNo(no);
	}

	public int update(BoardVO b) {
		// TODO Auto-generated method stub
		return DBManager.update(b);
	}
	
	public int delete(HashMap<String, Object> map) {
		return DBManager.delete(map);
	}

	public void updateStep(int b_ref, int b_step) {
		DBManager.updateStep(b_ref, b_step);
	}
}


















