package com.newleader.nlsite.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.common.HttpUtils;

/**
 * 二维码生成 
 * @author Luoshuhong
 * @Company  
 * 2015年9月15日
 *
 */
@Controller
@RequestMapping("/qrCode")
public class QrCodeProduce {
	static String APPID = "wx7da410e7b5d045fe";// 微信公众号
	static String SECRET = "296133f3ae1c0fb26daff3596014ec6c";// 应用密钥
	//获取token地址
	static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
	
	/**
	 * 主测试方法
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws  Exception {
		//System.out.println(permanentCode("flash1")); // 生成永久二维码
		
		//System.out.println(tempCode(1234)); //临时二维码
	}
	
	/**
	 * 永久二维码（请勿滥用生成，单个公众号，永久二维码有个数限制）
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	public static String permanentCode(String channelCode) throws Exception {
		String token = JSONObject.parseObject(doHttpGet(TOKEN_URL)).getString("access_token");
		String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
		
		//永久二维码
		String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": " + channelCode + "}}}";
		String result = QrCodeProduce.post(codeUrl, json);
		JSONObject resultJob = JSONObject.parseObject(result);
		// System.out.println(resultJob.getString("url"));
		if (null == resultJob) {
			return "";
		}
		return resultJob.getString("url");
	}
	
	/**
	 * 生成临时二维码
	 * @param channelCode  渠道编码
	 * @return 二维码地址
	 */
	public static String tempCode(int channelCode) throws Exception {
		String token = JSONObject.parseObject(doHttpGet(TOKEN_URL)).getString("access_token");
		String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
		//临时二维码
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+ channelCode+"}}}";
		String result = QrCodeProduce.post(codeUrl, json);
		JSONObject resultJob = JSONObject.parseObject(result);
		if (null == resultJob) {
			return "";
		}
		// System.out.println(resultJob.getString("url"));
		return resultJob.getString("url");
	}
	
	/**
	 * Http Post请求
	 * @param sendurl
	 * @param data
	 * @return
	 */
	public static String post(String sendurl, String data) {
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
	
	/**
	 * 
	 * @param urlStr
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String doHttpGet (String urlStr) throws MalformedURLException, IOException {
        URL url = new URL(urlStr);
        URLConnection c = url.openConnection();

        if (c instanceof HttpURLConnection) {
            HttpURLConnection httpConn = (HttpURLConnection) c;
            httpConn.setRequestMethod("GET");
            httpConn.setUseCaches(false);
            httpConn.setDoInput(true);
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setConnectTimeout(HttpUtils.DEFALT_TIMEOUT);
            httpConn.setReadTimeout(HttpUtils.DEFALT_TIMEOUT);

            // get response.
            InputStream is = httpConn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();

            httpConn.disconnect();
            return response.toString();
        } else {
            throw new MalformedURLException("Only HTTP/HTTPs is valid.");
        }
    }
}
