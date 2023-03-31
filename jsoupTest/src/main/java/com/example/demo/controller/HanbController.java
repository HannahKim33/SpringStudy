package com.example.demo.controller;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.NewBook;

@RestController
public class HanbController {
	
	
	
	@GetMapping("/seat")
	public String seat() {
		String url="http://mpllc-seat.sen.go.kr/seatinfo/Seat_Info/1_count.asp";
		String waiting="";
		try {
			Document doc=Jsoup.connect(url).get();
			//System.out.println(doc);
			//#Layer110 > table > tbody > tr > td
//			Elements e=doc.select("#Layer110 > table > tbody > tr > td");
//			System.out.println(e.get(0).text());
			//아이디가 중복되어 있어서 다른 글자가 나옴
			
			Elements list=doc.getElementsByClass("wating_f");
			waiting=list.get(0).text();
		}catch(Exception e) {
			System.out.println("예외발생"+e.getMessage());
		}
		return waiting;
	}
	
	@GetMapping("/newbook")
	public ArrayList<NewBook> newBook() {
		String url="https://www.hanbit.co.kr/store/books/new_book_list.html";
		ArrayList<NewBook> bookList=new ArrayList<>();
		try {
			Document doc= Jsoup.connect(url).get();
			Elements list=doc.getElementsByClass("book_tit");
			for(Element e:list) {
				Element a=e.getElementsByTag("a").get(0);
				String title=a.text();
				String link=a.attr("href");
				System.out.println(title);
				System.out.println(link);
				System.out.println("--------------------------------------");
				bookList.add(new NewBook(title,link));
				
			}
			//System.out.println(doc);
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return bookList;
	}
}
