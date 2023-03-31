package com.example.demo.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingController {
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@PostMapping("/search")
	public ModelAndView search(String keyword) {
		ModelAndView mav=new ModelAndView("/searchResult");
		String url="https://search.shopping.naver.com/search/all?frm=NVSHATC&origQuery="+keyword+"&pagingIndex=1&pagingSize=40&productSet=total&query="+keyword+"&sort=price_asc&timestamp=&viewType=list";
		
		try {
			Document doc=Jsoup.connect(url).get();
			String shopname=doc.getElementsByClass("basicList_mall__BC5Xu").get(0).text();
			String price_str=doc.getElementsByClass("price_num__S2p_v").get(0).text();
			String link=doc.getElementsByClass("basicList_link__JLQJf").get(0).attr("href");
			
			int price=Integer.parseInt(price_str.substring(0,price_str.length()-1).replace(",", ""));
			
			System.out.println(shopname);
			System.out.println(price);
			System.out.println(link);
			
			mav.addObject("shopname",shopname);
			mav.addObject("price",price);
			mav.addObject("link",link);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
}
