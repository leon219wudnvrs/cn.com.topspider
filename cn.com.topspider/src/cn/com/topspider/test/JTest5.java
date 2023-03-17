package cn.com.topspider.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//虎扑步行街热帖集合爬取
public class JTest5 {
	public static void main(String[] args) throws IOException {
		Document document=Jsoup.connect("https://bbs.hupu.com/all-gambia")
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

		//Element e1 = document.getElementById("container");
		//Elements select = e1.select("div > div.bbsHotPit");
//		Elements select = document.select("#container > div > div.bbsHotPit > div:nth-child(2)");
//		//System.out.println(select);
//		Elements select2 = select.select("h3 > span");
//		System.out.println(select2.text());
//		
//		Elements select3 = select.select("ul > li");
////		System.out.println(select3.get(0).text());
////		System.out.println(select3.get(0).select("a").get(0).attr("abs:href"));
//		int num1 =1;
//		for (Element element : select3) {
//			String title = element.text();
//			String href = element.select("a").get(0).attr("abs:href");
//			System.out.println("排名:"+num1+"  标题:"+title+"  链接:"+href);
//			num1 ++ ;
//		}
		
		
		for (int i = 2; i <= 5; i++) {
			Elements select = document.select("#container > div > div.bbsHotPit > div:nth-child("+i+")");
			Elements select2 = select.select("h3 > span");
			System.out.println("版块:"+select2.text());
			Elements select3 = select.select("ul > li");
			int num =1;
			for (Element element : select3) {
				String title = element.text();
				String href = element.select("a").get(0).attr("abs:href");
				System.out.println("排名:"+num+"  标题:"+title+"  链接:"+href);
				num ++ ;
			}
		}
		
		
	}
}
