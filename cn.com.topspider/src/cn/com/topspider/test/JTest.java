package cn.com.topspider.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JTest {
	private static OutputStream os;
	public static void main(String[] args) throws IOException {
		//getFav();
		getUrl();
		//getImg();
		//getHtmlDetil();
		
		
//		try
//		{
////		    Document document = Jsoup.connect("http://www.4399.com").get();
////		    System.out.println(document.title());
//			Document document = Jsoup.parse( new File( "c:/mydoc/分享文件.html" ) , "utf-8" );
//		    System.out.println(document.title());
//
//
//		} 
//		catch (IOException e) 
//		{
//		    e.printStackTrace();
//		}

	}
	
	//获取HTML页面的Fav图标
	static void getFav(){
		String favImage = "Not Found";
		try {
			Document document = Jsoup.connect("http://www.bilibili.com").get();
		    //Document document = Jsoup.parse(new File("C:/Users/zkpkhua/Desktop/yiibai-index.html"), "utf-8");
		    Element element = document.head().select("link[href~=.*\\.(ico|png)]").first();
		    if (element == null) 
		    {
		        element = document.head().select("meta[itemprop=image]").first();
		        if (element != null) 
		        {
		            favImage = element.attr("content");
		        }
		    } 
		    else
		    {
		        favImage = element.attr("href");
		    }
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		System.out.println(favImage);

	}
	
	//获取HTML页面中的所有链接
	static void getUrl(){
		try
		{
			Document document = Jsoup.connect("http://www.bilibili.com").get();
		    //Document document = Jsoup.parse(new File("C:/Users/zkpkhua/Desktop/yiibai-index.html"), "utf-8");
		    Elements links = document.select("a[href]");  
		    for (Element link : links) 
		    {
		         System.out.println("link : " + link.attr("href"));  
		         System.out.println("text : " + link.text());  
		    }
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
	}
	
	//获取HTML页面中的所有图像
	static void getImg(){
		try
		{
			Document document = Jsoup.connect("https://www.zhihu.com/question/297715922").get();
		    Elements images = document.select("img[src~=(?i)\\.(png|jpg|jpeg|gif)]");
		    for (Element image : images) 
		    {
		        System.out.println("src : " + image.attr("src"));
		        System.out.println("height : " + image.attr("height"));
		        System.out.println("width : " + image.attr("width"));
		        System.out.println("alt : " + image.attr("alt"));
		        String img = image.attr("src");
		        URL u = new URL(img);
				URLConnection uc=u.openConnection();
		        //获取数据流
		        InputStream is=uc.getInputStream();
		        //获取后缀名
		        String imageName = img.substring(img.lastIndexOf("/") + 1,img.length());
		        //写入本地
		        os = new FileOutputStream(new File("c:/mydoc/JsoupFile/img", imageName));
		        byte[] b = new byte[1024];
		        int i=0;
		        while((i=is.read(b))!=-1){
		          os.write(b, 0, i);
		        }
		        is.close();
		        os.close();
		    }
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	//获取URL的元信息
	static void getHtmlDetil(){
		try
		{
		    //Document document = Jsoup.parse(new File("C:/Users/zkpkhua/Desktop/yiibai-index.html"), "utf-8");
		    Document document = Jsoup.connect("http://www.bilibili.com").get();
		    String description = document.select("meta[name=description]").get(0).attr("content");  
		    System.out.println("Meta description : " + description);  

		    String keywords = document.select("meta[name=keywords]").first().attr("content");  
		    System.out.println("Meta keyword : " + keywords);  
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
}
