package cn.com.topspider.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//v站热点爬取
public class JTest4 {
	public static void main(String[] args) throws IOException {
		String cookie = "_ga=GA1.2.773577641.1675649699; A2=\"2|1:0|10:1675664995|2:A2|48:YmZlOGQ4MWItNzgwMC00ZTFkLTgyYTUtMmQ1MmE3ZTU1OWZh|499e42495148bbb2d3bd19036e0057b217a4491c86c691953ee898a3db505236\"; cf_clearance=kbd1J3iblIdvM_APyq0Rd3Dbk2kxMTNfB3eFXhGbPko-1675845360-0-150; V2EXTP=\"2|1:0|10:1677044450|6:V2EXTP|140:eyI5MTM5NzMiOiAyLCAiOTE2MjI1IjogMiwgIjg2MjI3NiI6IDIsICI5MTc4NTgiOiAyLCAiOTEzNDcyIjogMiwgIjkxMzA3MyI6IDIsICI4NDk2MTgiOiAyLCAiOTE0NjI0IjogM30=|b929f1b95d8a0d894c7ca67597fa8ebb769208ad73626e60d5cf4cf505ad9070\"; PB3_SESSION=\"2|1:0|10:1677554900|11:PB3_SESSION|40:djJleDoxMDQuMTQ5LjEzMC4yNTM6NTE1MDE0NzI=|82dacb17ed60f830feeb3d4d86cea33fa1b850218755ef2acb6d42d84b89c48b\"; V2EX_TAB=\"2|1:0|10:1677739199|8:V2EX_TAB|4:aG90|e629137dbac306cf492560fefb75d21020db80225cb1a249256b8c84bf78364a\"; V2EX_LANG=zhcn; V2EX_REFERRER=\"2|1:0|10:1677739221|13:V2EX_REFERRER|12:ZWNob2xlc3M=|254b2c138318aa714b99540580a934ec3d819103dff31f3a29209cf4bd8d3364\"";
		//https://www.v2ex.com/?tab=qna
		//https://v2ex.com/?tab=all
		//https://v2ex.com/recent?p=2
		Document document=Jsoup.connect("https://www.v2ex.com/?tab=qna")
                //模拟火狐浏览器
                //.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                //模拟谷歌浏览器
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .ignoreContentType(true).ignoreHttpErrors(true)
                .timeout(1000*30)
                .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding","gzip, deflate, br")
                .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                //.cookie("A2", "2|1:0|10:1574767104|2:A2|48:YmZlOGQ4MWItNzgwMC00ZTFkLTgyYTUtMmQ1MmE3ZTU1OWZh|4b97cef37bd1e3fac039ed8ae366b03152130df04ff7b400e29cb66b84c74b93")
				.proxy("127.0.0.1",10809)
				.header("cookie",cookie)
				.get();
		
		Element e1 = document.getElementById("Wrapper");
		Elements select = e1.select("#Main > div:nth-child(2) > div");
		System.out.println(select.size());
		
//		Elements select2 = select.select("div:nth-child(2) > table > tbody > tr > td:nth-child(3) > span.item_title > a");
//		System.out.println(select2);
//		String title = select2.text();
//		String href = select2.attr("abs:href");
//		System.out.println(href);
//		System.out.println(title);
		
//		int i = 2;
//		Iterator<Element> iterator = select.iterator();
//		if(iterator.hasNext()){
//			Elements select2 = iterator.next().
//					select("div:nth-child("+i+") > table > tbody > tr > td:nth-child(3) > span.item_title > a");
//			if(!select2.isEmpty()){
//				String title = select2.text();
//				String href = select2.attr("abs:href");
//				System.out.println("标题:"+title+"  链接:"+href);
//			}
//			i++;
//		}
		
		int num =1;
		for (Element element : select) {
			Elements select2 = element.
					select("table > tbody > tr > td:nth-child(3) > span.item_title > a");
			if(!select2.isEmpty()){
				String title = select2.text();
				String href = select2.attr("abs:href");
				System.out.println("排名:"+num+"  标题:"+title+"  链接:"+href);
				num++;
			}
		}
		
		//e1.select(query)
	}
}
