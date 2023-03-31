package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.swing.border.Border;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Board;

import lombok.Setter;

@Service
@Setter
public class BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	public void save(Board b) {
		dao.save(b);
	}
	
	public int getNextNo() {
		return dao.getNextNo();
	}
	
	public void insert(Board b) {
		dao.insert(b);
	}
	
	public List<Board> findAll(){
		return dao.findAll();
	}
	
	public Board findByNo(int no) {
		return dao.findById(no).get();
	}
	
	public void updateStep(int b_ref, int b_step) {
		dao.updateStep(b_ref, b_step);
	}
	
	public List<Board> findAllOrderBy(int start,int end){
		return dao.selectAll(start,end);
	}
	
	public int delete(int no, String pwd) {
		return dao.delete(no,pwd);
	}
	
	public int update(Board b) {
		return dao.update(b);
	}
	
	public int count() {
		return (int) dao.count();
	}
	
	public List<Board> selectAllById(int start,int end,String id){
		return dao.selectAllById(start,end,id);
	}

	public int countById(String id) {
		// TODO Auto-generated method stub
		return dao.countById(id);
	}
}
