package com.newleader.nlsite.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.common.HttpUtils;
import com.newleader.nlsite.common.QrCodeProduce;
import com.newleader.nlsite.common.RequestUtils;

/**
 * 二维码生成 
 * @author Luoshuhong
 * @Company  
 * 2015年9月15日
 *
 */
@Controller
@RequestMapping("/admin/qrCode")
public class QrCodeController {
	static String APPID = "wx7da410e7b5d045fe";// 微信公众号
	static String SECRET = "296133f3ae1c0fb26daff3596014ec6c";// 应用密钥
	//获取token地址
	static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
	
	@RequestMapping("/create")
    @ResponseBody
    public String crate(HttpServletRequest request, HttpServletResponse response){
		String url = "http://qr.liantu.com/api.php?logo=http://donottell.me/CSS/Images/homepage/logo.png&text=";
		
		String channelCode = request.getParameter("channelCode");
		String id = request.getParameter("id");
		
		if (StringUtils.isEmpty(channelCode) || StringUtils.isEmpty(id)) {
			return RequestUtils.failReturn("param is null");
		}
		
		String qrCodeLocalDir = QrCodeProduce.gerQrcodeLocalDir(request.getContextPath(), id, channelCode);
		//如果本地缓存有 直接用
		if (new File(request.getRealPath("/") + qrCodeLocalDir).exists()) {
			System.out.println("[local cache] localurl=" + request.getRealPath("/") + qrCodeLocalDir);
			return RequestUtils.successReturn("../" + qrCodeLocalDir);
		}
		
		//下面是重新生成
		try {
			String codeUrl = url + this.permanentCode(channelCode);  //生成永久二维码
			return RequestUtils.successReturn(codeUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	/**
	 * 永久二维码（请勿滥用生成，单个公众号，永久二维码有个数限制）
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	public String permanentCode(String channelCode) throws Exception {
		String token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
		String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
		
		//永久二维码
		String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + channelCode + "\"}}}";
		String result = HttpUtils.doHttpPost(codeUrl, json);
		JSONObject resultJob = JSONObject.parseObject(result);
		System.out.println("method=permanentCode,result=" + resultJob);
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
	@Deprecated
	public String tempCode(int channelCode) throws Exception {
		String token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
		String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
		//临时二维码
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+ channelCode+"}}}";
		String result = HttpUtils.doHttpPost(codeUrl, json);
		JSONObject resultJob = JSONObject.parseObject(result);
		System.out.println("method=tempCode,result=" + resultJob);
		if (null == resultJob) {
			return "";
		}
		return resultJob.getString("url");
	}
	
}
