package cn.com.topspider.test;


import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 
import java.io.*;
 
public class Spider_impage_in_page {
	
	String url;//要爬取网页的url
	String savePath;//文件路径
	String result = "";//存储爬取的结果
	String filename;//图片名||文件名
	String zhengze;//正则表达式
	LinkedList<String> imageURLList;//该网页下图片的连接
	
	public Spider_impage_in_page(String url ,String savePath ,String filename ,String zhengze) {
		// TODO 自动生成的构造函数存根
		
		this.url = url;
		this.savePath = savePath;
		this.filename = filename;
		this.zhengze = zhengze;
		imageURLList = new LinkedList<String>();
		
	}
	
	
 
	public void getImageInPage() {//下载该页面下的图片
		try {
			URL realUrl = new URL(url);
			//将url转换为实际的URL对象
			
			URLConnection connection = realUrl.openConnection();
			//初始化一个连接到URL的连接对象
			
			connection.connect();
			//连接
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			//初始化输入流
			
			String line = "";
			//从缓冲区中一次得到的信息
			
			while((line = in.readLine()) != null) {
				result += line;
				//获取结果
			}
			
			if(in != null) {
				//关闭缓冲区
				in.close();
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			System.out.println("爬取失败");
			e.printStackTrace();
			System.exit(0);
			
		}finally {
			//处理字符串
			dealResult(result,zhengze);
			int i = 1;
			while(!imageURLList.isEmpty()) {
				downloadImage(filename + i,imageURLList.removeFirst());
				i++;
			}
			
		}
		
	}
	
	public void dealResult(String targetStr , String patternStr) {//储存照片的URL
		//处理目标字符串，提取有用信息
		System.out.println(patternStr);
		Pattern pattern = Pattern.compile(patternStr);
		//用正则表达式设置陷阱2
		
		Matcher matcher = pattern.matcher(targetStr);
		//定义matcher匹配
		
		boolean isFind = matcher.find();
		while(isFind) {
			String dizhi = matcher.group(1);
			if(!imageURLList.contains(dizhi)) {
				imageURLList.add(dizhi);
			}
			isFind = matcher.find();
		}
		
	}
	
	public void downloadImage(String file , String urlstring) {		//下载图片
		
		String filename = file + ".jpg";
		String urlString = urlstring;
		URL url;
		
		try {
			url = new URL(urlString);
			// 打开连接
			
			URLConnection con = url.openConnection();
			
			
			con.setConnectTimeout(5*1000);
			//设置请求超时为5s
			
			InputStream is = con.getInputStream();
			// 输入流
 
			
			byte[] bs = new byte[1024];// 1K的数据缓冲
			int len;// 读取到的数据长度			
			File sf=new File(savePath + "/" +filename);// 输出的文件流
			OutputStream os = new FileOutputStream(sf);
			
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			
			os.close();// 完毕，关闭所有链接
			is.close();	
					
			} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
	}
	
	public static void main(String[] args) {
		String url = "https://www.zhihu.com/question/297715922";//目标页面
		String savePath = "d:/mydoc/JsoupFile2/img";//存储到该目录下
		String filename = "ss";//图片名
		String zhengze = " data-original=\\\"(.+?)\\\"";//正则表达式，这里我用的是" data-original=\\\"(.+?)\\\""
		
		Spider_impage_in_page zhihu = new Spider_impage_in_page(url, savePath ,filename ,zhengze);
		zhihu.getImageInPage();
	}

}