package com.newleader.nlsite.common;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

/**
 *  http 协议工具类
 * @author Luoshuhong
 * @Company  
 * 2015年6月7日
 *
 */
public class HttpUtils {
	public static final int DEFALT_TIMEOUT = 10 * 1000;
    
    /**
     * @param urlStr urlStr
     * @throws MalformedURLException MalformedURLException
     * @throws IOException IOException
     */
    public  static void doHttpGetNoReturn(String urlStr) throws MalformedURLException, IOException {
        URL url = new URL(urlStr);
        URLConnection c = url.openConnection();

        if (c instanceof HttpURLConnection) {
            HttpURLConnection httpConn = (HttpURLConnection) c;
            httpConn.setRequestMethod("GET");
            httpConn.setUseCaches(false);
            httpConn.setDoInput(true);
            httpConn.setConnectTimeout(HttpUtils.DEFALT_TIMEOUT);
            httpConn.setReadTimeout(HttpUtils.DEFALT_TIMEOUT);
            HttpURLConnection.setFollowRedirects(true);
          
        } else {
            throw new MalformedURLException("Only HTTP/HTTPs is valid.");
        }
    }
    
    /**
     * @param urlStr urlStr
     * @return String
     * @throws MalformedURLException MalformedURLException
     * @throws IOException IOException
     */
    public static String doHttpPost (String urlStr) throws MalformedURLException, IOException {
        URL url = new URL(urlStr);
        URLConnection c = url.openConnection();

        if (c instanceof HttpURLConnection) {
            HttpURLConnection httpConn = (HttpURLConnection) c;
            httpConn.setRequestMethod("POST");
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
    
    /**
     * 
     * @param urlStr urlStr
     * @return String
     * @throws MalformedURLException MalformedURLException
     * @throws IOException IOException
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
    
    /**
	 * Http Post请求
	 * @param sendurl
	 * @param data
	 * @return
	 */
	public static String doHttpPost(String sendurl, String data) {
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
	 * 远程下载图片到本地
	 * @param baseDir   本地目录
	 * @param fileName  本地生成的文件名
	 * @param urlString 远程url
	 * @return 是否成功
	 */
	public static boolean download(String baseDir, String fileName, String urlString) {
		InputStream is = null;     // 输入流
		OutputStream os = null;    // 输出的文件流
		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			con.setConnectTimeout(HttpUtils.DEFALT_TIMEOUT);   // 链接超时时间30秒
			con.setReadTimeout(HttpUtils.DEFALT_TIMEOUT);      // 写入超时时间30秒
			is = con.getInputStream();// 获得输入流
			byte[] bs = new byte[1024];// 1K的数据缓冲
			
			File dirPath = new File(baseDir);
			if (!dirPath.exists()) {
                dirPath.mkdir();
            }
			os = new FileOutputStream(new File(baseDir + fileName));
			// 开始读取
			int len;
			while ((len = is.read(bs)) != -1) {
			  os.write(bs, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
	    	try {
				if(os != null){
					os.close();
				}
				if(is != null){
					is.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}	
		}
		return true;
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
        post.setEntity(myEntity); //设置请求体
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
