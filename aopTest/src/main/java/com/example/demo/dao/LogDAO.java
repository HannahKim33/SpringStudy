package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.LogVO;

@Repository
public class LogDAO {
	public int insertLog(LogVO lvo) {
		return DBManager.insertLog(lvo);
	}
}
