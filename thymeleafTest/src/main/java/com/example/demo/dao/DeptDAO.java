package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.DeptVO;

@Repository
public class DeptDAO {
	public List<DeptVO> findAll(){
		return DBManager.findAll();
	}
	
	public DeptVO findById(int dno){
		return DBManager.findById(dno);
	}
	
	public int insert(DeptVO d) {
		return DBManager.insertDept(d);
	}
	public int update(DeptVO d) {
		return DBManager.updateDept(d);
	}
	
	public int delete(int dno) {
		return DBManager.deleteDept(dno);
	}
}
