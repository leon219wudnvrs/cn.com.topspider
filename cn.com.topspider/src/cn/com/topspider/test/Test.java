package cn.com.topspider.test;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws IOException {

        Document document=Jsoup.connect("https://www.thisamericanlife.org/793/transcript")
                //模拟火狐浏览器
//                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                //模拟谷歌浏览器
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
                .ignoreContentType(true).ignoreHttpErrors(true)
//				.cookies(map)
                .timeout(1000*30)
                .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-enxcoding","gzip, deflate, br")
                .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .get();

//        System.out.println(document);
        String title = document.select("#block-system-main > div > article > header > h1").text();
        //播客标题
        System.out.println(title);
        String note = document.select("#block-system-main > div > article > header > div.field-name-field-notes > p").text();
        //播客注释
        System.out.println(note);
        System.out.println();
        Elements actElements = document.select("#block-system-main > div > article > div.content > div.act");
        for (Element act : actElements) {
            //播客章节
            String actName = act.select("h3").text();
            System.out.println(actName);
            Elements innerElements = act.select("div.act-inner > div");
            for (Element inner : innerElements) {
                String personName = inner.select("h4").text();
                //人名
                System.out.println(personName);
                Elements p = inner.select("p");
                for (Element textEl: p) {
                    //说话内容
                    String personText = textEl.text();
                    System.out.println(personText);
                }
                System.out.println();
            }
        }
        document.select("#thread_theme_5 > div.l_thread_info > ul > li:nth-child(2)");
    }
}
