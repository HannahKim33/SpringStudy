package com.example.jpatest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpatest.dao.GoodsDAO;
import com.example.jpatest.vo.GoodsVO;

import lombok.Setter;

@Service
@Setter
public class GoodsService {
	
	@Autowired
	private GoodsDAO dao;
	
	public List<GoodsVO> findAll(){
		return dao.findAll();
	}
	
	public void save(GoodsVO g) {
		dao.save(g);
	}
	
	public GoodsVO findById(int no) {
		return dao.findById(no).get();
	}
	
	public void delete(int no) {
		dao.deleteById(no);
	}
}
