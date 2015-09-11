package com.newleader.nlsite.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newleader.nlsite.common.HttpUtils;

@Controller
@RequestMapping("/wx")
public class WxRedirectControler {
	
	@RequestMapping("/redirect")
    @ResponseBody
    public void add(HttpServletRequest request, HttpServletResponse response){
		String appid = "wx7da410e7b5d045fe"; // 微信公众号
		String secret = "296133f3ae1c0fb26daff3596014ec6c";// 应用密钥
        
		String channelId = request.getParameter("code");
		String code = request.getParameter("state");
		System.out.println("method=getUserInfo,step=start,code=" + code + ",cId=" + channelId);

		code = code == null ? "null" : code;
		channelId = channelId == null ? "null" : channelId;
		
		//通过code获取openid
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID", appid);
		url = url.replace("SECRET", secret);
		url = url.replace("CODE", code);
		try {
			System.out.println("method=getUserInfo,step=getOpenId start,url=" + url);
			String result = HttpUtils.doHttpGet(url);
			System.out.println("method=getUserInfo,step=getOpenId end,result=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//重定向
		try {
			response.sendRedirect("http://www.zhe800.com/");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		String token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7da410e7b5d045fe&secret=296133f3ae1c0fb26daff3596014ec6c";
//		String appid = "wx7da410e7b5d045fe"; // 微信公众号
//		String secret = "296133f3ae1c0fb26daff3596014ec6c";// 应用密钥
//		System.out.println(HttpUtils.doHttpGet(token));
		
		String t = "zbU6STbyGosy5i2psQqUz3jRLQ2MBXnrXchSRNtsB2GIcq69PBfIo5NZ3joeKx6hiqD2mtfs8TFdUExovTLCY-rajgW5iwpezofmn9BWAvE";
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + t;
		String json = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 63254}}}";
//		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 4443}}}";
//		System.out.println(Test.sendInfo(url, json));
	}
	
}	
