package com.example.demo.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name="board")
public class Board {
	@Id
	private int no;
	private String title, writer, pwd, content;
	private Date regdate;
	private int hit;
	private String ip;
	
	private int b_ref,b_step,b_level;
	
	@Transient	//DB에는 이 칼럼 만들지 마
	private MultipartFile uploadFile;
	private String fname;
}
