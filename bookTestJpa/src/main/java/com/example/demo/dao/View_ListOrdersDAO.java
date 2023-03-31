package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.vo.View_ListOrders;

public interface View_ListOrdersDAO extends JpaRepository<View_ListOrders, Integer> {

	public List<View_ListOrders> findByNameContaining(String keyword);
	
	@Query("select v from View_ListOrders v order by v.orderid")
	public List<View_ListOrders> findAllOrderByOrderid();
	
	@Query("select v from View_ListOrders v order by v.name")
	public List<View_ListOrders> findAllOrderByName();
	
	@Query("select v from View_ListOrders v order by v.bookname")
	public List<View_ListOrders> findAllOrderByBookname();
	
	@Query("select v from View_ListOrders v order by v.orderdate")
	public List<View_ListOrders> findAllOrderByOrderdate();
	
	@Query("select v from View_ListOrders v order by v.saleprice")
	public List<View_ListOrders> findAllOrderBySaleprice();
	
	@Query("select v from View_ListOrders v order by v.price")
	public List<View_ListOrders> findAllOrderByPrice();
	
//	@Query("select v from View_ListOrders v where v.name=?1")
//	public List<View_ListOrders> searchName(String keyword);

}
