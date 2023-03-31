package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.DeptVO;
import com.example.demo.vo.EmpVO;

@Repository
public class DeptDAO {
	public List<DeptVO> findAll() {
		return DBManager.findAllDept();
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
