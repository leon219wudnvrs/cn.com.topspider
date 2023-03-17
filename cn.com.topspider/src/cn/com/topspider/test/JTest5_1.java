package cn.com.topspider.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//虎扑步行街热帖爬取
public class JTest5_1 {
	public static void main(String[] args) throws IOException, InterruptedException {
		Document document=Jsoup.connect("https://bbs.hupu.com/31047504.html")
                //模拟火狐浏览器
                //.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                //模拟谷歌浏览器
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .ignoreContentType(true).ignoreHttpErrors(true)
                .timeout(1000*30)
                .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding","gzip, deflate, br")
                .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .get();
		
		//Elements e1 = document.getElementsByClass("hp-wrap details");
		//Elements select = e1.select("#t_main");
		
		
		Elements select2 = document.select("#j_data");
		Elements sspan = document.select("#t_main > div.bbs_head > div.bbs-hd-h1 > span.browse");
		String title = select2.text();
//		String text3 = sspan.eachText();
		System.out.println("标题:"+title);
		System.out.println(sspan.eachText());
		
		Elements select = document.select("#tpc > div > div.floor_box > table.case > tbody > tr > td > div.quote-content");
		//Elements select = document.select("#tpc");
		//Elements select3 = select.select("div > div.floor_box > table.case > tbody > tr > td > div.quote-content");
		Elements select3 = select.select("p");
		System.out.println("-----------楼主-----------");
		for (Element element : select3) {
			String text = element.text();
			Elements img = element.select("img");
			
			if(!text.isEmpty()){
				System.out.println(text);
			}
			if(!img.isEmpty()){
				System.out.println(img.attr("src"));
			}
		}
		
		System.out.println("-----------这些回复亮了-----------");
		Elements select4 = document.select("#readfloor > div");
		//Elements select5 = select4.select("div[class=floor]");
		System.out.println(select4.size()+"个热评");
		
		for (Element element : select4) {
			//Element element = select4.get(4);
			Elements select5 = element.select("div.floor_box > div.author > div.left");
			System.out.println(select5.text());
//			String lightUser = select5.select("a.u").text();
//			String stime = select5.select("span.stime").get(0).text();
//			String text = select5.select("span.ilike_icon_list").text();
//			System.out.println(lightUser);
//			System.out.println(stime);
//			System.out.println(text);
			
			Elements select6 = element.select("div.floor_box > table > tbody > tr > td");
			Elements blockquote = select6.select("blockquote");
			if(!blockquote.isEmpty()){
				Elements select7 = blockquote.select("p");
				for (Element element1 : select7) {
					String text1 = element1.text();
					Elements img1 = element1.select("img");
					
					if(!text1.isEmpty()){
						System.out.println(text1);
					}
					if(!img1.isEmpty()){
						System.out.println(img1.attr("src"));
					}
				}
				System.out.println("---");
				select7.remove();
				Elements select8 = select6.select("small");
				String text2 = "";
				if(!select8.isEmpty()){
					text2 = select8.text();
					select8.remove();
				}
				System.out.println(select6.text());
				Elements select9 = select6.select("p > img");
				if(!select9.isEmpty()){
					for (Element element2 : select9) {
						String attr = element2.attr("src");
						if(!attr.isEmpty()){
							System.out.println(attr);
						}
					}
				}
//				String attr = select6.select("p").attr("src");
//				if(!attr.isEmpty()){
//					System.out.println(attr);
//				}
				if(text2!=""){
					System.out.println(text2);
				}
				
			}else{
				Elements select7 = select6.select("p");
				for (Element element1 : select7) {
					String text1 = element1.text();
					Elements img1 = element1.select("img");
					
					if(!text1.isEmpty()){
						System.out.println(text1);
					}
					if(!img1.isEmpty()){
						System.out.println(img1.attr("src"));
					}
				}
				
//				Elements select8 = select6.select("small");
//				if(!select8.isEmpty()){
//					System.out.println(select8.text());
//				}
				
				Elements select8 = select6.select("small");
				String text ="";
				if(!select8.isEmpty()){
					//System.out.println(select8.text());
					text = select8.text();
				}
				if(select7.isEmpty()){
					select6.remove("small");
					System.out.println(select6.text());
				}
				
				if(text!=""){
					System.out.println(text);
				}
				
			}
			System.out.println("————————————————————————————————————————");
		}
		
		
		System.out.println("-----------正常楼层-----------");
		Elements select5 = document.select("#t_main > form > div.floor");
		System.out.println(select5.size());
		Elements tpc = document.select("#tpc");
		if(!tpc.isEmpty()){
			select5.remove(0);
		}
		System.out.println(select5.size());
		for (Element element : select5) {
			Elements selectc = element.select("div.floor_box > div.author > div.left");
			System.out.println(selectc.text());
//			String lightUser = select5.select("a.u").text();
//			String stime = select5.select("span.stime").get(0).text();
//			String text = select5.select("span.ilike_icon_list").text();
//			System.out.println(lightUser);
//			System.out.println(stime);
//			System.out.println(text);
			
			Elements select6 = element.select("div.floor_box > table > tbody > tr > td");
			Elements blockquote = select6.select("blockquote");
			if(!blockquote.isEmpty()){
				Elements select7 = blockquote.select("p");
				for (Element element1 : select7) {
					String text1 = element1.text();
					Elements img1 = element1.select("img");
					
					if(!text1.isEmpty()){
						System.out.println(text1);
					}
					if(!img1.isEmpty()){
						System.out.println(img1.attr("src"));
					}
				}
				System.out.println("---");
				select7.remove();
				Elements select8 = select6.select("small");
				String text2 = "";
				if(!select8.isEmpty()){
					text2 = select8.text();
					select8.remove();
				}
				System.out.println(select6.text());
				Elements select9 = select6.select("p > img");
				if(!select9.isEmpty()){
					for (Element element2 : select9) {
						String attr = element2.attr("src");
						if(!attr.isEmpty()){
							System.out.println(attr);
						}
					}
				}
//				String attr = select6.select("p").attr("src");
//				if(!attr.isEmpty()){
//					System.out.println(attr);
//				}
				if(text2!=""){
					System.out.println(text2);
				}
				
			}else{
				Elements select7 = select6.select("p");
				for (Element element1 : select7) {
					String text1 = element1.text();
					Elements img1 = element1.select("img");
					
					if(!text1.isEmpty()){
						System.out.println(text1);
					}
					if(!img1.isEmpty()){
						System.out.println(img1.attr("src"));
					}
				}
				
				Elements select8 = select6.select("small");
				String text ="";
				if(!select8.isEmpty()){
					//System.out.println(select8.text());
					text = select8.text();
				}
				if(select7.isEmpty()){
					select6.remove("small");
					System.out.println(select6.text());
				}
				
				if(text!=""){
					System.out.println(text);
				}
			}
			System.out.println("————————————————————————————————————————");
		}
	}
}
