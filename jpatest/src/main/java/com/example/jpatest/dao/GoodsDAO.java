package com.example.jpatest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpatest.vo.GoodsVO;

@Repository
public interface GoodsDAO extends JpaRepository<GoodsVO, Integer> {
	
}
