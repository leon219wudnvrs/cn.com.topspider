import com.baidu.translate.demo.TransApi;

import java.io.UnsupportedEncodingException;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20230313001598122";
    private static final String SECURITY_KEY = "SkI2Gns1ugupiV3xAQ3n";

    //{"src":"genre","dst":"体裁"} {"src":"rational","dst":"合理的"} {"src":"rescued","dst":"营救"} {"src":"persuade","dst":"劝说"}
    //{"src":"military","dst":"军事的"} {"src":"wires","dst":"电线"} {"src":"dangling","dst":"晃来晃去的"} {"src":"ricocheting","dst":"弹回"}
    //"src":"frail","dst":"脆弱的"} {"src":"yearn","dst":"渴望"} {"src":"platelets","dst":"血小板"} {"src":"rural","dst":"乡村的"}
    //{"src":"desperately","dst":"拼命地"} {"src":"irrelevant","dst":"无关的"} {"src":"sobbing","dst":"抽泣"} {"src":"tipping point","dst":"临界点"}
    //{"src":"tolerate","dst":"容许"} {"src":"invaded","dst":"入侵"} {"src":"auction","dst":"拍卖"} {"src":"invasion","dst":"武装入侵"}
    //{"src":"vague","dst":"模糊的"} {"src":"propaganda","dst":"宣传"} {"src":"minors","dst":"未成年人"} {"src":"absurd","dst":"荒谬的"}
    //{"src":"specifically","dst":"明确地"} {"src":"targeted specifically","dst":"专门针对的"} {"src":"protest","dst":"抗议"} {"src":"funeral","dst":"葬礼"}
    //{"src":"startled","dst":"受惊的"} {"src":"occur","dst":"发生"} {"src":"couch","dst":"沙发"} {"src":"permission","dst":"准许"}
    //{"src":"spouse","dst":"配偶"} {"src":"self-soothing","dst":"自我安慰的"} {"src":"glitch","dst":"差错"} {"src":"exploited","dst":"剥削"}
    //{"src":"sexual scandal","dst":"性丑闻"} {"src":"interracial","dst":"跨种族"} {"src":"disclose","dst":"披露"} {"src":"exhaustive","dst":"详尽的"}
    //{"src":"enraged","dst":"愤怒的"} {"src":"siblings","dst":"兄弟姐妹"}
    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "Seance";
        try {
            System.out.println(api.getTransResult(query, "auto", "zh"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
