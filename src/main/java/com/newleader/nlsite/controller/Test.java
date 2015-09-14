package com.newleader.nlsite.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.common.HttpUtils;

public class Test {
	public static void main(String[] args) throws MalformedURLException, IOException {
//		String appid = "wx7da410e7b5d045fe"; // 微信公众号
//		String secret = "296133f3ae1c0fb26daff3596014ec6c";// 应用密钥
		String token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7da410e7b5d045fe&secret=296133f3ae1c0fb26daff3596014ec6c";
		JSONObject job = JSONObject.parseObject(HttpUtils.doHttpGet(token));
		String t = job.getString("access_token");
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + t;
//		永久二维码
		String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"zhilian0916\"}}}";
		//临时二维码
//		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 1314}}}";
		String result = Test.sendInfo(url, json);
		JSONObject resultJob = JSONObject.parseObject(result);
		System.out.println(resultJob.getString("url"));
	}
	
	public static String sendInfo(String sendurl, String data) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(sendurl);
        StringEntity myEntity = new StringEntity(data,
                ContentType.APPLICATION_JSON);// 构造请求数据
        post.setEntity(myEntity);// 设置请求体
        String responseContent = null; // 响应内容
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (client != null)
                        client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseContent;
    }
}
