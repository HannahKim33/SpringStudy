package com.example.demo.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Entity
@Data
@Table(name = "board")
public class Board {
	@Id
	 private int no;
	
	 private String title;

	 @ManyToOne
	 @JoinColumn(name = "id", insertable = true, updatable = true)
	 private Member member;
	 
	 private String pwd;
	 private String content;
	 private Date regdate;
	 private int hit;
	 private String ip;
	 
	 private int b_ref;
	 private int b_step;
	 private int b_level;
	 
	 @Transient
	 private MultipartFile uploadFile;
	 private String fname;
}

