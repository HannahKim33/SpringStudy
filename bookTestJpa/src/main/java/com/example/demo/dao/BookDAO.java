package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BookVO;

@Repository
public interface BookDAO extends JpaRepository<BookVO, Integer>{
//	public List<BookVO> findByBookname(String Bookname);
//	public List<BookVO> findByBooknameLike(String bookname);
	
	public List<BookVO> findByBookidGreaterThan(int bookid);
	public List<BookVO> findByBooknameContaining(String bookname);
	public List<BookVO> findByPublisherContaining(String bookname);
	public List<BookVO> findByPriceGreaterThan(int price);
}
