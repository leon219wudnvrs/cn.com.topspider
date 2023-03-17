package cn.com.topspider.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupDemo {
	private static OutputStream os;
	
	public static void main(String[] args) {
		try {
//			Document doc = Jsoup.connect("https://www.zhihu.com/question/297715922").get();
			Document doc = Jsoup.connect("http://moe.005.tv/67388.html").get();
			System.out.println("网站标题:"+doc.title()); 
			//把文章标题和连接写入txt文件
			//Element feedlist_id = doc.getElementById("root");
			//Elements h2 = feedlist_id.select("data-zop-usertoken");
			Elements elements = doc.select("#root");
			Elements a = elements.select("div").select("div:nth-child(2)");
			//Elements a = select.select("a");
			//指定文件名及路径
			File file = new File("c:/mydoc/JsoupFile/test.txt"); 
			if (!file.exists()) {
				file.createNewFile();
			}
			//写入本地
			PrintWriter pw = new PrintWriter("c:/mydoc/JsoupFile/test.txt","UTF-8"); 
			for (Element element : a) {
				pw.println(element.text());
				pw.println(element.attr("href")); 
				pw.println("------------------------------------------------------------------------------------------------------------------------------------");
			}
			pw.close(); //关闭输出流
			//获取页面上的图片保存到本地
			//Elements imgs = doc.select("img[src$=.png]");
			Elements imgs = doc.select("img[src~=(?i)\\.(png|jpg|jpeg|gif)]");
//			Elements test=doc.select("#QuestionAnswers-answers")
//					.select("div").select("div").select("div").select("div:nth-child(2)").select("div").select("div:nth-child(5)").select("div")
//					.select("div.RichContent.RichContent--unescapable").select("div.RichContent-inner")
//					.select("span").select("figure:nth-child(46)").select("img[src~=(?i)\\.(png|jpg|jpeg|gif)]");;
			//Elements test=doc.select("#root").select("div").select("main").select("div").select("div:nth-child(11)").select("div").select("img[src~=(?i)\\.(png|jpg|jpeg|gif)]");
			for (Element element : imgs) {
				String img = element.attr("src");
				String url = img;
				System.out.println(url);
				System.out.println(url.indexOf("csdn"));
//				if (url.indexOf("csdn")==-1) {
//					continue;
//				}
				URL u = new URL(url);
				URLConnection uc=u.openConnection();
		        //获取数据流
		        InputStream is=uc.getInputStream();
		        //获取后缀名
		        String imageName = img.substring(img.lastIndexOf("/") + 1,img.length());
		        System.out.println(imageName);
		        //写入本地
		        os = new FileOutputStream(new File("c:/mydoc/JsoupFile/img", imageName));
		        System.out.println("os---");
		        byte[] b = new byte[1024];
		        int i=0;
		        while((i=is.read(b))!=-1){
		          os.write(b, 0, i);
		        }
		        is.close();
		        os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
