package com.newleader.nlsite.common;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.model.Channel;

/**
 * 二维码生成 
 * @author Luoshuhong
 * @Company  
 * 2015年9月15日
 *
 */
public class QrCodeProduce {
	static String APPID = "wx7da410e7b5d045fe";// 微信公众号
	static String SECRET = "296133f3ae1c0fb26daff3596014ec6c";// 应用密钥
	//获取token地址
	static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
	
	//生成二维码图片地址
	static String QR_CORD_URL = "http://qr.liantu.com/api.php?logo=http://donottell.me/CSS/Images/homepage/logo.png&text=";
	//二维码图片存放目录
	static String QR_CODE_DIR = "image/qrcode/";
	static String QR_CODE_LOGO = "image/logo.jpg";
	
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
	 * 生成二维码
	 * @param list
	 */
	public static void createQrCord(String baseDir,Channel channel) {
		String url = "http://qr.liantu.com/api.php?logo=http://donottell.me/CSS/Images/homepage/logo.png&text=";
		String id = channel.getId();
		String code = channel.getCode().trim().replace(" ", "");
		
		if (StringUtils.isEmpty(channel.getQrCodeUrl())) {
			return;
		}
		try {
			String fileName = id + "_" + code + ".png";
//			HttpUtils.download(baseDir + QR_CODE_DIR , fileName, url + channel.getQrCodeUrl());
			QRCoder.createQrCode(channel.getQrCodeUrl(), baseDir + QR_CODE_LOGO, baseDir + QR_CODE_DIR + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取二维码图片 本地路径
	 * @param id   渠道id
	 * @param code 渠道编码
	 * @return  本地路径
	 */
	public static String gerQrcodeLocalDir(String baseDir,String id, String code) {
//		return baseDir + QR_CODE_DIR + id + "_" + code + ".png";
		return QR_CODE_DIR + id + "_" + code.trim().replace(" ", "") + ".png";
	}
	
	/**
	 * 永久二维码（请勿滥用生成，单个公众号，永久二维码有个数限制）
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	public static String permanentCode(String channelCode)   {
		try {
			String token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
			String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
			//永久二维码
			String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + channelCode + "\"}}}";
			String result = HttpUtils.post(codeUrl, json);
			JSONObject resultJob = JSONObject.parseObject(result);
			if (null == resultJob) {
				return "";
			}
			return resultJob.getString("url");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 生成临时二维码
	 * @param channelCode  渠道编码
	 * @return 二维码地址
	 */
	public static String tempCode(int channelCode) throws Exception {
		String token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
		String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
		//临时二维码
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+ channelCode+"}}}";
		String result = HttpUtils.post(codeUrl, json);
		JSONObject resultJob = JSONObject.parseObject(result);
		if (null == resultJob) {
			return "";
		}
		return resultJob.getString("url");
	}
}
