package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CustomerVO;

@Repository
public class CustomerDAO {
	public List<CustomerVO> findAll(){
		return DBManager.findAll();
	}
	public CustomerVO findByCustid(int custid) {
		return DBManager.findByCustid(custid);
	}
	public int insert(CustomerVO c) {
		int re=DBManager.insert(c);
		return re;
	}
	public int update(CustomerVO c) {
		return DBManager.update(c);
	}
	public int delete(int custid) {
		return DBManager.delete(custid);
	}
}
