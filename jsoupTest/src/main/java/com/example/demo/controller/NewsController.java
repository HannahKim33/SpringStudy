package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.News;

@RestController
public class NewsController {
	
	public String shop() {
		
	}
	
	@GetMapping("/imgDown")
	public String imgDOwn() {
		String addr="https://shared-comic.pstatic.net/thumb/webtoon/758037/thumbnail/thumbnail_IMAG21_15cb2611-34c0-4f02-a689-41d0b1016579.jpg";
		String fname="참교육";
		downloadImage(addr, fname);
		return "OK";
	}
	
	public String allImgDown() {
		
		return "OK";
	}
	
	@GetMapping("/imgDownAll")
	public String imgDownAll() {
		String addr="https://comic.naver.com/webtoon/weekday";

		try {
			Document doc=Jsoup.connect(addr).get();
			Elements list=doc.getElementsByClass("list_area daily_all").get(0).getElementsByTag("img");
			
			for(Element e:list) {
				String src=e.attr("src");
				String fname=e.attr("title");
				
				fname=fname.replace("?", "");
				fname=fname.replace(":", "");
				
				/*
				System.out.println(src);
				System.out.println(fname);
				System.out.println("-----------");
				*/
				downloadImage(src, fname);
				/*
				FileOutputStream fos=new FileOutputStream(path+"/"+fname+".jpg");
				URL url= new URL(src);
				InputStream is=url.openStream();
				FileCopyUtils.copy(is.readAllBytes(), fos);
				fos.close();
				*/
			}
			System.out.println("모두 다운받았습니다");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}		
		return "OK";
	}
	
	//이미지 하나 다운받기
	/*
	@GetMapping("/imgDown")
	public String imgDown() {
		String addr="https://shared-comic.pstatic.net/thumb/webtoon/758037/thumbnail/thumbnail_IMAG21_15cb2611-34c0-4f02-a689-41d0b1016579.jpg";
		try {
			String path="c:/webtoon";
			String fname="참교육";
			FileOutputStream fos=new FileOutputStream(path+"/"+fname+".jpg");
			URL url= new URL(addr);
			InputStream is=url.openStream();
			FileCopyUtils.copy(is.readAllBytes(), fos);
			fos.close();
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return "OK";
	}
	*/
	public void downloadImage(String addr, String fname) {
		
		try {
			String path="c:/webtoon2";
			FileOutputStream fos=new FileOutputStream(path+"/"+fname+".jpg");
			URL url= new URL(addr);
			InputStream is=url.openStream();
			FileCopyUtils.copy(is.readAllBytes(), fos);
			fos.close();
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
	@GetMapping("/news")
	public ArrayList<News> news() {
		ArrayList<News> news_list=new ArrayList<>();
		String url="https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=230";
		try {
			Document doc = Jsoup.connect(url).get();
			int last_page=Integer.parseInt(doc.select("#main_content > div.paging > a:last-child").text());
			System.out.println("last_page:"+last_page);
			String base="https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=230&sid1=105&date=20221223&page=";
			for (int i=1;i<=last_page;i++) {
				String url2=base+i;
				Document doc2=Jsoup.connect(url2).get();
				
				Elements list=doc2.select("#main_content > div.list_body.newsflash_body").get(0).getElementsByTag("a");
				
				for(Element e:list) {
					String title=e.text();
					String link=e.attr("href");
					if(title!=null && !title.equals("")) {
						news_list.add(new News(title,link));
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return news_list;
	}
	
	
	/*
	@GetMapping("/news")
	public ArrayList<News> news() {
		//#main_content > div.paging > a:last-child
		String url="https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=230";
		Document doc;
		int last_page=0;
		ArrayList<News> news_list=new ArrayList<>();
		try {
			doc = Jsoup.connect(url).get();
			//System.out.println(doc);
			//Elements list=doc.getElementsByClass("list_tit nclicks('rig.renws2')");
			last_page=Integer.parseInt(doc.select("#main_content > div.paging > a:nth-child(8)").text());
			Elements list=doc.select("#main_content > div.list_body.newsflash_body").get(0).getElementsByTag("a");
			
			for(Element e:list) {
				String title=e.text();
				String link=e.attr("href");
				if(title!=null && !title.equals("")) {
//					System.out.println(title);
//					System.out.println(link);
//					System.out.println("------------------");
					news_list.add(new News(title,link));
				}
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		for(int i=2;i<=last_page;i++) {
			url="https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=230&sid1=105&date=20221223&page="+i;
			try {
				doc = Jsoup.connect(url).get();
				Elements list=doc.select("#main_content > div.list_body.newsflash_body").get(0).getElementsByTag("a");
				
				for(Element e:list) {
					String title=e.text();
					String link=e.attr("href");
					if(title!=null && !title.equals("")) {
						System.out.println(title);
						System.out.println(link);
						System.out.println("------------------");
						news_list.add(new News(title,link));
					}
					
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return news_list;
	}
	*/
}
