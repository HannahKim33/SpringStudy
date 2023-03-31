package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.OrdersViewID;
import com.example.demo.vo.View_ListOrders2;

public interface View_ListOrdersDAO2 extends JpaRepository<View_ListOrders2, OrdersViewID> {

}
