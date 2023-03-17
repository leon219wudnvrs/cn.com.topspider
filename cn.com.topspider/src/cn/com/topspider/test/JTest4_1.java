package cn.com.topspider.test;

import java.io.IOException;
import java.net.Proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//v站贴子爬取
public class JTest4_1 {
	public static void main(String[] args) throws IOException {
		//https://v2ex.com/t/917858?p=2
		//https://www.v2ex.com/t/917652
		//https://v2ex.com/t/920345#reply37
		String cookie = "_ga=GA1.2.773577641.1675649699; A2=\"2|1:0|10:1675664995|2:A2|48:YmZlOGQ4MWItNzgwMC00ZTFkLTgyYTUtMmQ1MmE3ZTU1OWZh|499e42495148bbb2d3bd19036e0057b217a4491c86c691953ee898a3db505236\"; cf_clearance=kbd1J3iblIdvM_APyq0Rd3Dbk2kxMTNfB3eFXhGbPko-1675845360-0-150; V2EXTP=\"2|1:0|10:1677044450|6:V2EXTP|140:eyI5MTM5NzMiOiAyLCAiOTE2MjI1IjogMiwgIjg2MjI3NiI6IDIsICI5MTc4NTgiOiAyLCAiOTEzNDcyIjogMiwgIjkxMzA3MyI6IDIsICI4NDk2MTgiOiAyLCAiOTE0NjI0IjogM30=|b929f1b95d8a0d894c7ca67597fa8ebb769208ad73626e60d5cf4cf505ad9070\"; PB3_SESSION=\"2|1:0|10:1677554900|11:PB3_SESSION|40:djJleDoxMDQuMTQ5LjEzMC4yNTM6NTE1MDE0NzI=|82dacb17ed60f830feeb3d4d86cea33fa1b850218755ef2acb6d42d84b89c48b\"; V2EX_TAB=\"2|1:0|10:1677739199|8:V2EX_TAB|4:aG90|e629137dbac306cf492560fefb75d21020db80225cb1a249256b8c84bf78364a\"; V2EX_LANG=zhcn; V2EX_REFERRER=\"2|1:0|10:1677739221|13:V2EX_REFERRER|12:ZWNob2xlc3M=|254b2c138318aa714b99540580a934ec3d819103dff31f3a29209cf4bd8d3364\"";
		Document document=Jsoup.connect("https://www.v2ex.com/t/920421")
                //模拟火狐浏览器/t/623499
                //.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                //模拟谷歌浏览器
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .ignoreContentType(true).ignoreHttpErrors(true)
                .timeout(1000*30)
                .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding","gzip, deflate, br")
                .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
				.proxy("127.0.0.1",10809)
				.header("cookie",cookie)
                .get();

		Elements boxSelect = document.select("#Main > div:nth-child(2)");
		//System.out.println(boxSelect);
		
		//标题
		Elements titleSelect= boxSelect.select("div.header > h1");
		String title = titleSelect.text();
		System.out.println("标题:");
		System.out.print(title);
		
		//标题信息
		Elements detailSelect = boxSelect.select("div.header > small");
		String titleDetail = detailSelect.text();
		System.out.println("-----"+titleDetail);
		
		////标题内容
		Elements contentSelect = boxSelect.select("div.cell");
		String titleContent = contentSelect.text();
		System.out.println("标题内容:");
		System.out.println(titleContent);
		
		//标题附言
		Elements subtleSelect = boxSelect.select("div.subtle");
		System.out.println("标题附言:");
//		Elements topCttSelect = subtleSelect.select("div.topic_content");
//		System.out.println(topCttSelect);
		for (Element subtleElement : subtleSelect) {
			String subtle = subtleElement.text();
			System.out.println(subtle);
		}
		
		//回复区
		System.out.println("-------------------------------------------------------------");
		//intro
		Elements box2Select = document.select("#Main > div:nth-child(4)");
		Elements spanselect = box2Select.select("div:nth-child(1) > span.gray");
		String intro = spanselect.text();
		System.out.println("{"+intro+"}");
		
		//评论
		Elements comentsSelect = box2Select.select("div.cell");
		System.out.println(comentsSelect.size());
		//comentsSelect.remove(0);
		System.out.println(comentsSelect.size());
		
//		Elements te = comentsSelect.get(0).select("table > tbody > tr > td:nth-child(3)");
//		Elements userElement = te.select("strong");
//		String user = userElement.text();
//		Elements agoElement = te.select("span.ago");
//		String agoStr = agoElement.text();
//		Elements likeCountElement = te.select(" span.small.fade");
//		String likeCount = likeCountElement.text();
//		Elements replyElmt = te.select("div.reply_content");
//		String replyStr = replyElmt.text();
//		System.out.println(user+" "+agoStr+" "+likeCount);
//		System.out.println(replyStr);
		
		
//		for (Element comentElement : comentsSelect) {
//			comentElement.select("table > tbody > tr > strong");
//		}
		for (int i = 1; i < comentsSelect.size(); i++) {
			System.out.println("——————————————————————————");
			Elements te = comentsSelect.get(i).select("table > tbody > tr > td:nth-child(3)");
			Elements userElement = te.select("strong");
			String user = userElement.text();
			Elements agoElement = te.select("span.ago");
			String agoStr = agoElement.text();
			Elements likeCountElement = te.select("span.small.fade");
			String likeCount = likeCountElement.text();
			Elements replyElmt = te.select("div.reply_content");
			String replyStr = replyElmt.text();
			System.out.println(i+"楼："+user+" "+agoStr+" "+likeCount);
			System.out.println(replyStr);
		}
		
		
		}
	
		
		
}
