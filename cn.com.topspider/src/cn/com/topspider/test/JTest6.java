package cn.com.topspider.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//贴吧贴子爬取
public class JTest6 {
	public static void main(String[] args) throws IOException {
		//https://tieba.baidu.com/f?kw=steam&ie=utf-8&tab=main
		String tiebaName = "专升本";
		String cookieStr = "BIDUPSID=23E819AC9FA78A7B48ADEB6B5E1FC458; PSTM=1658713813; BAIDUID=23E819AC9FA78A7B98775A5B1B254703:SL=0:NR=10:FG=1; BDUSS=ENhN3YwQ2p0TWxkU0Z-S2JaSHR0Q1kxQldTQ3hhY0dQenRWZGUzQXdhS08yQWxqSVFBQUFBJCQAAAAAAAAAAAEAAADp9SlOkNXUigAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI5L4mKOS-Jid; BDUSS_BFESS=ENhN3YwQ2p0TWxkU0Z-S2JaSHR0Q1kxQldTQ3hhY0dQenRWZGUzQXdhS08yQWxqSVFBQUFBJCQAAAAAAAAAAAEAAADp9SlOkNXUigAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI5L4mKOS-Jid; STOKEN=f4f566768784d0065f06569ad943fae1872a7698d7de17987e1bfd2dbc01fa3d; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BAIDUID_BFESS=23E819AC9FA78A7B98775A5B1B254703:SL=0:NR=10:FG=1; ZFY=YbIsVGZe3e5WlBPZGxXnrm6THzJVK2f3V55WOEL:B418:C; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; delPer=0; PSINO=5; BA_HECTOR=ak21ag8h0k2404alal827msr1hggpnj16; wise_device=0; Hm_lvt_98b9d8c2fd6608d564bf2ac2ae642948=1660809916,1661247129,1661308698,1661501211; st_key_id=17; H_PS_PSSID=37156_36551_36464_36885_34812_36805_37134_26350_37201_37228; BAIDU_WISE_UID=wapp_1661501543838_172; USER_JUMP=-1; 1311372777_FRSVideoUploadTip=1; video_bubble1311372777=1; ab_sr=1.0.1_YTViNGMzYjFiZmEwNjU3M2NkM2VhMTQ5Mzc5ZWZlMTVhN2Y3MzZhNzExMTE2ODY2ODhlYjc2MWM4ZGQyMDYyZjYxYTdmZGJjMWE0ZDgzMWFiNjkwMzdkZTBkMjNmZmZhN2ExNzMzMDI3NjQzZmY4OGRlNjdjMTJkMTRhMGY4NGViM2FmN2FmODg4MTA3NmE3MjA1MDZkNDEwNDgxYzgzOGExYjFiN2RiYzMzNTg3MWMwN2VmMDRkNzkxYzBiM2Zh; st_data=fdb13c455c458a3d09686b71bb75ace55260fd07b5b7ad7c4af1b6eef5fc021c085f9c2709ba40f7c24b01f43156236c8d093f92c4e51e9a6eab1c3bf2bef83ab1116058623efc4dc6d349c986507b2ba985c05575a7757ef253ace9cbc45326; st_sign=08706165; RT=\"z=1&dm=baidu.com&si=9c016a73-4a10-4572-b583-4854dfe802a9&ss=l7a74cax&sl=1&tt=cf&bcn=https%3A%2F%2Ffclog.baidu.com%2Flog%2Fweirwood%3Ftype%3Dperf&nu=4bm0kzkw&cl=3glpw&ld=32e\"; Hm_lpvt_98b9d8c2fd6608d564bf2ac2ae642948=1661501555";
		Document document=Jsoup.connect("https://tieba.baidu.com/f?kw="+tiebaName+"&ie=utf-8&tab=main")
                //模拟火狐浏览器
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                //模拟谷歌浏览器
                //.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .ignoreContentType(true).ignoreHttpErrors(true)
                .timeout(1000*30)
                .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding","gzip, deflate, br")
                .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
				.header("Cookie",cookieStr)
                .get();
		
//		Elements thread_list = document.select("#thread_list");
		
//		Elements liSelect = document.select("#thread_list > li:nth-child(2)");
		
//		//回复数
//		Elements replyElmt = liSelect.select("div > div.col2_left.j_threadlist_li_left");
//		String replyCount = replyElmt.text();
//		//System.out.println(replyCount+"回复");
//		
//		
//		Elements rightElmt = liSelect.select("div > div.col2_right.j_threadlist_li_right");
//		
//		//标题
//		Elements titleElmt = rightElmt.select("div.threadlist_lz.clearfix > div.threadlist_title.pull_left.j_th_tit");
//		String title = titleElmt.text();
//		System.out.println("标题："+title);
//		
//		//作者
//		Elements authorElmt = rightElmt.select("div.threadlist_lz.clearfix > div.threadlist_author.pull_right");
//		String author = authorElmt.select("span").get(0).attr("title");
//		//System.out.println(author);
//		//时间
//		String time = authorElmt.select("span.pull-right.is_show_create_time").text();
//		//System.out.println(time);
//		
//		//详情
//		Elements detailElmt = rightElmt.select("div.threadlist_detail.clearfix");
//		String detail = detailElmt.select("div.threadlist_text.pull_left").text();
//		//System.out.println(detail);
//		
//		System.out.println(author+"   "+time+"   "+replyCount+"回复");
//		System.out.println("详情："+detail);
		
		//Elements liSelect = thread_list.select("li");
		//System.out.println(thread_list.size());
		int sort= 1;
		for (int i = 2; i <= 52; i++) {
			Elements liSelect = document.select("#thread_list > li:nth-child("+i+")");
			
			if(liSelect.isEmpty()){
//				break;
				continue;
			}
			
			//回复数
			Elements replyElmt = liSelect.select("div > div.col2_left.j_threadlist_li_left");
			String replyCount = replyElmt.text();
			//System.out.println(replyCount+"回复");
			
			
			Elements rightElmt = liSelect.select("div > div.col2_right.j_threadlist_li_right");
			
			//标题
			Elements titleElmt = rightElmt.select("div.threadlist_lz.clearfix > div.threadlist_title.pull_left.j_th_tit");
			String title = titleElmt.text();
			System.out.println("标题："+title);
			//链接
			String href = titleElmt.select("a").attr("abs:href");
			
			//作者
			Elements authorElmt = rightElmt.select("div.threadlist_lz.clearfix > div.threadlist_author.pull_right");
			String author = authorElmt.select("span").get(0).attr("title");
			//System.out.println(author);
			//时间
			String time = authorElmt.select("span.pull-right.is_show_create_time").text();
			//System.out.println(time);
			
			//详情
			Elements detailElmt = rightElmt.select("div.threadlist_detail.clearfix");
			String detail = detailElmt.select("div.threadlist_text.pull_left").text();
			//System.out.println(detail);
			
			System.out.println(author+"   "+time+"   "+replyCount+"回复");
			System.out.println("详情："+detail);
			System.out.println("链接："+href);
			System.out.println("顺序："+sort);
			System.out.println("————————————————————————————————————————————————");
			sort ++;
		}
		
	}
}
