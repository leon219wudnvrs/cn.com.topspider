package com.openai;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809));
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey("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")
                .connectTimeout(50)
                .writeTimeout(50)
                .readTimeout(50)
//                .interceptor(Arrays.asList(httpLoggingInterceptor))
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build();
        //代理
        //简单模型
//        CompletionResponse completions = openAiClient.completions("介绍一下佳锐科技吧");
//        Arrays.stream(completions.getChoices()).forEach(System.out::println);
        //最新GPT-3.5-Turbo模型
        Message message = Message.builder().role(Message.Role.USER).content("介绍一下佳锐科技吧").build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        chatCompletionResponse.getChoices().forEach(e -> {
            System.out.println(e.getMessage());
        });
    }
}
