package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

import lombok.Setter;

@Service
@Setter
public class BookService {
	
	@Autowired
	private BookDAO dao;
	
	public List<BookVO> findAll(){
		return dao.findAll();
	}
	
	public void save(BookVO b) {
		dao.save(b);
	}
	
	public BookVO getOne(int bookid) {
		return dao.getOne(bookid);
	}
	
	public void delete(int bookid) {
		dao.deleteById(bookid);
	}
	
//	public List<BookVO> findByBookname(String bookname) {
//		return dao.findByBookname(bookname);
//	}
//	
//	public List<BookVO> findByBooknameLike(String bookname){
//		return dao.findByBooknameLike(bookname);
//	}
	
	public List<BookVO> findByBookidGreaterThan(int bookid){
		return dao.findByBookidGreaterThan(bookid);
	}
	
	public List<BookVO> findByBooknameContaining(String bookname){
		return dao.findByBooknameContaining(bookname);
	}
	
	public List<BookVO> findByPublisherContaining(String bookname){
		return dao.findByPublisherContaining(bookname);
	}
	
	public List<BookVO> findByPriceGreaterThan(int price){
		return dao.findByPriceGreaterThan(price);
	}

	public List<BookVO> search(String keyword, String col) {
		List<BookVO> list=null;
		switch(col) {
		case "bookname":
			list=dao.findByBooknameContaining(keyword);break;
		case "bookid":
			list=dao.findByBookidGreaterThan(Integer.parseInt(keyword));break;
		case "publisher":
			list=dao.findByPublisherContaining(keyword);break;
		case "price":
			list=dao.findByPriceGreaterThan(Integer.parseInt(keyword));break;
		}
		return list;
	}
}
