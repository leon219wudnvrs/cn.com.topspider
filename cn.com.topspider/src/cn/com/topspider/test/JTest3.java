package cn.com.topspider.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//百度热搜爬取
public class JTest3 {
	public static void main(String[] args) throws IOException {
		//Document document = Jsoup.parse(new URL("http://top.baidu.com/buzz?b=1").openStream(), "GBK", "http://top.baidu.com/buzz?b=1");
        //获取编辑推荐页
        Document document=Jsoup.connect("http://top.baidu.com/buzz?b=1")
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
        
        Elements e1 = document.getElementsByClass("wrapper");
        Elements select = e1.select("div").select("#main > div.mainBody > div > table > tbody > tr");
        
//        Elements select2 = select.select(":nth-child("+30+") > td.keyword");
//        System.out.println(select2.select("a").get(0));
//        System.out.println(select2.select("a").get(0).attr("href"));
//        System.out.println(select2.select("a").get(0).text());
        //System.out.println(select.toString());
       
        //#main > div.mainBody > div > table > tbody > tr:nth-child(8) > td.keyword
        //Elements select2 = select.select("tr:nth-child(8) > td.keyword");
        
        int i=1;
        int num=1;
        for (Element element : select) {
        	Elements select2 = element.select(":nth-child("+i+") > td.keyword");
        	//System.out.println(select2);
        	if(!select2.isEmpty()){
	        	String title = select2.select("a").get(0).text();
	        	//String encode = URLEncoder.encode(title, "gb2312");
	        	String string = new String(title.getBytes("gb2312"), "GBK");
	        	String href = select2.select("a").get(0).attr("href");
	        	System.out.println("排名:"+num+"  标题:"+string+"  链接:"+href);
	        	num++;
        	}
        	i++;
		}
        
//        Element main=document.getElementById("zh-recommend-list-full");
//        Elements url=main.select("div").select("div:nth-child(2)")
//                .select("h2").select("a[class=question_link]");
//        for(Element question:url){
//            //输出href后的值，即主页上每个关注问题的链接
//            String URL=question.attr("abs:href");
//            //下载问题链接指向的页面
//            Document document2=Jsoup.connect(URL)
//                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
//                    .get();
//            //问题
//            Elements title=document2.select("#zh-question-title").select("h2").select("a");
//            //问题描述
//            Elements detail=document2.select("#zh-question-detail");
//            //回答
//            Elements answer=document2.select("#zh-question-answer-wrap")
//                    .select("div.zm-item-rich-text.expandable.js-collapse-body")
//                    .select("div.zm-editable-content.clearfix");
//            System.out.println("\n"+"链接："+URL
//                    +"\n"+"标题："+title.text()
//                    +"\n"+"问题描述："+detail.text()
//                    +"\n"+"回答："+answer.text());
//        }   
    }

}
