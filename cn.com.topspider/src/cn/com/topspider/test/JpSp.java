package cn.com.topspider.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JpSp {
		public static void main(String[] args) {
			zh();
		}
		
	    public static void zh(){
	 		int imgCount=0;
	 		for(int si=0*20;si<=1*20;si++){	//si从第几条数据开始(索引从0开始)，每页20条数据，页码=第几页*每页条数(1*20=第一页)298
	 			 try {
	 				 
	 	            // 封装请求参数
	 	            String json = "{\"include\": \"data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,is_labeled,is_recognized;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics\",\n" +
	 	                    //"\"limit\": 20,\n" + //每列显示的个数
	 	                    "\"limit\": 20,\n" + //每列显示的个数
	 	                    //"\"offset\": 15,\n" + //页数
	 	                    "\"offset\": "+si+",\n" + //页数
	 	                    "\"platform\": \"desktop\",\n" +
	 	                    "\"sort_by\": \"default\"}";
	 	            JSONObject object = JSONObject.parseObject(json);
	 	 
	 	            // 使用jsoup对url参数发起请求
	 	            //Connection connect = Jsoup.connect("https://www.zhihu.com/api/v4/questions/297715922/answers");
	 	            //Connection connect = Jsoup.connect("https://www.zhihu.com/api/v4/questions/30061914/answers");
	 	            //Connection connect = Jsoup.connect("https://www.zhihu.com/api/v4/questions/26037846/answers");
	 	           Connection connect = Jsoup.connect("https://www.zhihu.com/api/v4/questions/54571814/answers");
	 	           //Connection connect = Jsoup.connect("https://www.zhihu.com/api/v4/questions/55910163/answers");
	 	            
	 	            // 将封装的参数放至jsoup 作为发送请求的参数  例：?limit: 20&offset: 15
	 	            for (String key : object.keySet()) {
	 	                connect.data(key,object.get(key).toString());
	 	            }
	 	 
	 	            // 发起请求并接受响应
	 	            Connection.Response response = connect
	 	                    // 防止 UnsupportedMimeTypeException 异常
	 	                    .ignoreContentType(true)
	 	                    // 伪装
	 	                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
	 	                    .execute();
	 	 
	 	            // 解析响应体
	 	            JSONObject responseJson = JSONObject.parseObject(response.body());
	 	            JSONArray dataArray = JSONArray.parseArray(responseJson.get("data").toString());
	 	 
	 	            /**
	 	             * 文件io操作
	 	             */
	 	 
	 	            // 创建 C:\Users\Admin\Desktop 文件夹
	 	            File file = new File("d:/mydoc/JsoupFile/img");
	 	            if (!file.exists()) {
	 	                file.mkdirs();
	 	            }
	 	 
	 	            // 创建 C:\Users\Admin\Desktop\zh.txt 文件
	 	            File ZH = new File("d:/mydoc/JsoupFile/zh.txt");
	 	            if (!ZH.isFile()){
	 	                ZH.createNewFile();
	 	            }
	 	 
	 	            // 文件输出流&高效输出流  用于将数据写入到指定文件中
	 	            FileWriter fileWriter = new FileWriter(ZH,true);
	 	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	 	 
	 	            // 从返回的响应体中过滤想要的数据并写入到文件中
	 	            int count =0;
	 	            long startTime = new Date().getTime();
	 	            for (Object data : dataArray) {
	 	                JSONObject dataJO = JSONObject.parseObject(data.toString());
	 	                JSONObject author = JSONObject.parseObject(dataJO.get("author").toString());
	 	                Object authorName = author.get("name");
	 	                
	 	                Object excerpt = dataJO.get("content");
	 	                
	 	                //test
	 	                String cts = dataJO.get("content").toString();
	 	                
	 	                if(Integer.parseInt(dataJO.get("voteup_count").toString())>0){  //回答赞同数超过0
	 	                count++;	 //用户数
	 	                String regex ="img[src~=(?i)\\.(png|jpg|jpeg|gif)]";
	 	                regex=" data-original=\\\"(.+?)\\\"";
//	 	                regex="https+:[^\\\\s]+(.jpg|.png)";
	 	                List<String> list = new ArrayList<String>();
	 	        		List<String> extvounoLists = new ArrayList<String>();
	 	        		Pattern pattern = Pattern.compile(regex);
	 	        		Matcher m = pattern.matcher(cts);
	 	        		while (m.find()) {  
	 	                    int i = 1;
	 	                    System.out.println("筛选地址:"+m.group(i));
	 	                    list.add(m.group(i));  
	 	                    i++;  
	 	                }
	 	        		
//	 	        		while (m.find()) {  
//	 	                    int i = 1;
//	 	                    System.out.println("Screening of address:"+m.group());
//	 	                    list.add(m.group());  
//	 	                    i++;  
//	 	                }
	 	        		
	 	        		
	 	        		
	 	        		//list去重
	 	        		for (int i = 0; i < list.size()-1; i++) {
	 	        			 for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {       
	 	        		           if  (list.get(j).equals(list.get(i)))  {       
	 	        		              list.remove(j);       
	 	        		            }        
	 	        		      }        
						}	
	 	        		
	 	        		for (String str : list) {
	 	        			System.out.println(str);
	 	        			URL u = new URL(str);
	 	        			String name = str.substring(str.lastIndexOf("/") + 1,str.length());
	 						URLConnection uc=u.openConnection();
	 				        //获取数据流
	 				        InputStream is;
	 						try {
	 							is = uc.getInputStream();
	 						} catch (Exception e) {
	 							// TODO Auto-generated catch block
	 							e.printStackTrace();
	 							continue;
	 						}
	 				   
	 				        //写入本地
	 						if(new File("d:/mydoc/JsoupFile/img", name).exists()){
	 							System.out.println("已存在");
	 							continue;
	 						}
	 				        FileOutputStream os = new FileOutputStream(new File("d:/mydoc/JsoupFile/img", name));
	 				        System.out.println("用户名---"+authorName);
	 				        //System.out.println("os---");
	 				        byte[] b = new byte[1024];
	 				        int i=0;
	 				        while((i=is.read(b))!=-1){
	 				          os.write(b, 0, i);
	 				        }
	 				        imgCount++;
	 				        is.close();
	 				        os.close();
	 	        			String[] strs = str.split("-");
	 	        			//String strss = strs[strs.length-1];
	 	        			extvounoLists.add(strs[strs.length-1]);
	 	        		}
	 	        		System.out.println(authorName+"已存入："+list.size()+"条数据");
	 	        		
	 	        		for (String string : extvounoLists) {
	 	        			//System.out.println(string);
	 	        			if(string.contains("https")){
	 	        				//System.out.println(string);
	 	        			}
	 	        			
	 	        		}
	 	        		
	 	        		
	 	        		
	 	
	 	               // System.out.println();
	 	                Object voteup_count = dataJO.get("voteup_count");
	 	                // 将数据写入缓存区
	 	                bufferedWriter.write(authorName+"("+voteup_count+"人赞同):");
	 	                
	 	                bufferedWriter.write("\n");
	 	                bufferedWriter.write(excerpt.toString());
	 	                bufferedWriter.write("\n");
	 	                bufferedWriter.write("\n");
	 	                bufferedWriter.write("\n");
	 	        		}
	 	            }
	 	 
	 	            // 刷新   注：将缓存区的数据刷新到文件中
	 	            bufferedWriter.flush();
	 	 
	 	            // 关流   注：节约资源
	 	            fileWriter.close();
	 	            bufferedWriter.close();
	 	            System.out.println("已存入用户回答："+count);
	 	            
	 	            long endTime = new Date().getTime();
		 			System.out.println("总花费时间："+(endTime-startTime)/1000);
	 	            }
	 	         catch (Exception e) {
	 	            e.printStackTrace();
	 	        }
	 			si+=20;
	 		}
	 		System.out.println("总共已存入："+imgCount);
	       

	 	}
}
