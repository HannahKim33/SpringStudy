package com.example.jpatest.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="goods")
public class GoodsVO {
	@Id
	private int no;
	private String name;
	private int qty;
	private int price;
	private String fname;
	
}
