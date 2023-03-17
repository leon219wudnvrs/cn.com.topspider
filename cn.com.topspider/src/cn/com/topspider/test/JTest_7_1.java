package cn.com.topspider.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JTest_7_1 {
    public static void main(String[] args) throws IOException {
        Document document= Jsoup.connect("https://news.zhibo8.cc/zuqiu/2023-02-22/63f565667a5a8.htm")
                //模拟火狐浏览器
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                //模拟谷歌浏览器
                //.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .ignoreContentType(true).ignoreHttpErrors(true)
                .timeout(1000*30)
                .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding","gzip, deflate, br")
                .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .get();

        System.out.println(document);
    }
}
