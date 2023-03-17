package cn.com.topspider.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JTest6_1 {
	public static void main(String[] args) throws IOException {
		//https://tieba.baidu.com/p/7970928673 https://tieba.baidu.com/p/7970185339
		//https://tieba.baidu.com/p/7977910708 https://tieba.baidu.com/p/8017686762
		//https://tieba.baidu.com/p/8018300231 https://tieba.baidu.com/p/8029003346
		//https://tieba.baidu.com/p/8286344424 https://tieba.baidu.com/p/8286380636
		//https://tieba.baidu.com/p/6289730011 https://tieba.baidu.com/p/8311540849
		String tieziUrl = "https://tieba.baidu.com/p/7909416011"; //帖子url
		String[] urlAry = tieziUrl.split("/");
		String tid = urlAry[urlAry.length-1];
		String pn = "1"; //页码
		String cookieStr = "BIDUPSID=23E819AC9FA78A7B48ADEB6B5E1FC458; PSTM=1658713813; BAIDUID=23E819AC9FA78A7B98775A5B1B254703:SL=0:NR=10:FG=1; BDUSS=ENhN3YwQ2p0TWxkU0Z-S2JaSHR0Q1kxQldTQ3hhY0dQenRWZGUzQXdhS08yQWxqSVFBQUFBJCQAAAAAAAAAAAEAAADp9SlOkNXUigAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI5L4mKOS-Jid; BDUSS_BFESS=ENhN3YwQ2p0TWxkU0Z-S2JaSHR0Q1kxQldTQ3hhY0dQenRWZGUzQXdhS08yQWxqSVFBQUFBJCQAAAAAAAAAAAEAAADp9SlOkNXUigAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI5L4mKOS-Jid; MCITY=-218%3A; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BAIDUID_BFESS=23E819AC9FA78A7B98775A5B1B254703:SL=0:NR=10:FG=1; ZFY=:BS:A:BhBLmuGXvvYNiS7ln1E6Y67qN8Y2QFuHZH1mOYus:C; BAIDU_WISE_UID=wapp_1678948554433_998; arialoadData=false; RT=\"z=1&dm=baidu.com&si=e4a187f7-f2fb-446b-a587-75f35ff985eb&ss=lfaqmarb&sl=2&tt=5pa&bcn=https%3A%2F%2Ffclog.baidu.com%2Flog%2Fweirwood%3Ftype%3Dperf&ld=6e6&ul=786&hd=78m\"; BA_HECTOR=2l840h002521ak840g81ak6r1i15nan1m; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; PSINO=5; delPer=0; H_PS_PSSID=38185_36551_38106_38354_38401_37862_38171_38289_38240_36805_37938_38315_38284_26350_38281_37881; ab_sr=1.0.1_NmYyNmZhY2QwMTM5MDYzY2Q2ZGE1OWZjOGU0MzgzYmFiNjg1NTkxYmJlMzU3ZDVlYzJiNTNkMzZlMTQwMGU4YjAzODg0MDA0ZTM0OGI5ZTdkNTQ4YjUwMWFiNTgyMWMwZjg2ZTVjYWJiMTQzMGQzZTRiZDZjMjk0NTNmZDQ1ZGMxZDQyM2IyMTI5YTcyYzA4ZTQyY2FiMjY3OTc0YWNhNmNlMDg5ODI1M2U0ZmRlN2IzODI1NWEzMzU3ZmRlZmYx";
		String[] cookieArray = cookieStr.split(";");
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < cookieArray.length; i++) {
			String ck = cookieArray[i];
			String[] ckArray = ck.split("=");
			map.put(ckArray[0],ckArray[1]);
		}

		Document document=Jsoup.connect(tieziUrl + "?pn=" + pn)
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
				.header("Cookie",cookieStr)
                .get();
		
		//回复数
		Elements reply_numSelect = document.select("#thread_theme_5 > div.l_thread_info > ul > li:nth-child(2)");
		String reply_numS = reply_numSelect.text();
		System.out.println("回复数："+reply_numS);
		
		//标题
		Elements core_titleSelect = document.select("#j_core_title_wrap > h3");
		String core_title = core_titleSelect.text();
		System.out.println("标题："+core_title);
		
		//楼层
		Elements postlistSelect = document.select("#j_p_postlist");
		Elements listSelect = postlistSelect.select("div.l_post.l_post_bright");
		//listSelect.remove(1);	//去除广告
		System.out.println(listSelect.size());
		
		for (Element postElmt : listSelect) {
			//Element postElmt = listSelect.get(1);
			
			Elements authorSelect = postElmt.select("div.d_author");
			//楼主标识
			Elements louzhubiaoshi = authorSelect.select("div.louzhubiaoshi_wrap");
			if(!louzhubiaoshi.isEmpty()){
				String lz = "【楼主】";
				System.out.println(lz);
			}
			//用户
			Elements p_authorSelect = authorSelect.select("ul.p_author");
			//用户名
			Elements d_nameSelect = p_authorSelect.select("li.d_name");
			String d_name = d_nameSelect.text();
			System.out.println(d_name);
			//等级头衔
			Elements p_badgeSelect = p_authorSelect.select("li.l_badge > div.p_badge");
			//头衔
			Elements d_badge_title = p_badgeSelect.select("a > div.d_badge_title");
			String badge_title = d_badge_title.text();
			//等级
			Elements d_badge_lv = p_badgeSelect.select("a > div.d_badge_lv");
			String badge_lv = d_badge_lv.text();
			System.out.println("["+badge_title+"   LV"+badge_lv+"]");

			//评论
			Elements contentSelect = postElmt.select("div.d_post_content_main");
			
			Elements contentElmt = contentSelect.select("div.p_content");
			Elements post_contentEl = contentElmt.select("div.d_post_content");
			String p_content = post_contentEl.text();
			System.out.println(p_content);

			//图片
			Elements imgSelect = post_contentEl.select("img");
			if(!imgSelect.isEmpty()) {
				imgSelect.forEach(img -> {
					System.out.println(img.attr("src"));
				});
			}
			//楼层、时间
			Elements core_replySelect = contentSelect.select("div.core_reply");
			Elements tailSelect = core_replySelect.select("div.post-tail-wrap");
			System.out.println(tailSelect.text());
			//回复core_reply_wrapper  div.core_reply_content
//			Elements reply_contentSelect = core_replySelect.select("div.core_reply_wrapper");
//			Elements select = contentSelect.select("div.core_reply_wrapper");
//			System.out.println(select.text());

			//获取评论楼中楼 (爬不到。。).select("div.j_lzl_container.core_reply_wrapper");
			Elements replyElmt = contentSelect.select("div.core_reply.j_lzl_wrapper");
			String data_field = replyElmt.select("div.core_reply_tail.clearfix").select("div.post-tail-wrap")
					.select("div.j_lzl_r.p_reply").attr("data-field");
			postElmt.attr("data-pid");
			postElmt.attr("data-field");
			if(StringUtils.isNotEmpty(data_field)){
				Long pid = (Long)JSONObject.parseObject(data_field).get("pid");
				Integer total_num = (Integer) JSONObject.parseObject(data_field).get("total_num");
				if(total_num != null && total_num != 0) {

					int num = total_num / 10 + 1;

					System.out.println("###############   楼中楼开始    ###############");

					for (int i = 1; i <= num; i++) {
						Random random = new Random();
						String replyUrl = "https://tieba.baidu.com/p/comment?tid=" + tid + "&pid=" + pid + "&pn=" + i + "&t=" + random.nextInt(1000000000);
						Document replyDoc = Jsoup.connect(replyUrl)
								//模拟谷歌浏览器
								.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
								.ignoreContentType(true).ignoreHttpErrors(true)
								.timeout(1000 * 30)
								.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
								.header("accept-enxcoding", "gzip, deflate, br")
								.header("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
								.get();

						Elements replyList = replyDoc.select("li.lzl_single_post");
						replyList.forEach(reply -> {
							String text = reply.text();
							System.out.println(text);
						});
						System.out.println("***************   当前页码：【" + i + "】   ***************");
					}

					System.out.println("###############   楼中楼结束    ###############");
					System.out.println();
				}
			}

			System.out.println("————————————————————————————————————————————————");
		}
		
//		Element postElmt = listSelect.get(1);
//		
//		Elements authorSelect = postElmt.select("div.d_author");
//		//楼主标识
//		Elements louzhubiaoshi = authorSelect.select("div.louzhubiaoshi_wrap");
//		if(!louzhubiaoshi.isEmpty()){
//			String lz = "【楼主】";
//			System.out.println(lz);
//		}
//		//用户
//		Elements p_authorSelect = authorSelect.select("ul.p_author");
//		//用户名
//		Elements d_nameSelect = p_authorSelect.select("li.d_name");
//		String d_name = d_nameSelect.text();
//		System.out.println(d_name);
//		//等级头衔
//		Elements p_badgeSelect = p_authorSelect.select("li.l_badge > div.p_badge");
//		//头衔
//		Elements d_badge_title = p_badgeSelect.select("a > div.d_badge_title");
//		String badge_title = d_badge_title.text();
//		//等级
//		Elements d_badge_lv = p_badgeSelect.select("a > div.d_badge_lv");
//		String badge_lv = d_badge_lv.text();
//		System.out.println("["+badge_title+"   LV"+badge_lv+"]");
//		
//		//评论
//		Elements contentSelect = postElmt.select("div.d_post_content_main");
//		
//		Elements contentElmt = contentSelect.select("div.p_content");
//		Elements post_contentEl = contentElmt.select("div.d_post_content");
//		String p_content = post_contentEl.text();
//		System.out.println(p_content);
//		//图片
//		Elements imgSelect = post_contentEl.select("img");
//		if(!imgSelect.isEmpty()){
//			System.out.println(imgSelect.attr("src"));
//		}
//		//楼层、时间
//		Elements core_replySelect = contentSelect.select("div.core_reply");
//		Elements tailSelect = core_replySelect.select("div.post-tail-wrap");
//		System.out.println(tailSelect.text());
//		//回复core_reply_wrapper  div.core_reply_content
//		Elements reply_contentSelect = core_replySelect.select("div.core_reply_wrapper");
//		Elements select = core_replySelect.select("div.j_lzl_container.core_reply_wrapper > div.j_lzl_c_b_a.core_reply_content");
//		System.out.println(core_replySelect.select("div.core_reply_wrapper"));
		
		
		
	}
}
