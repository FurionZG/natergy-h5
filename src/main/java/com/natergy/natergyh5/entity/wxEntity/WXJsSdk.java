package com.natergy.natergyh5.entity.wxEntity;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WXJsSdk {
	//定义静态常量存放获取AccessToken的URL
	public final static String GET_PAGE_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
	public final static String GET_PAGE_JS_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/*
	 * 获取JsApiTicket
	 **/
	public Map<String, String> getJsapiTicket(String accessToken) {
	     String requestUrl = GET_PAGE_JS_API_TICKET_URL.replace("ACCESS_TOKEN", accessToken);
	     HttpClient client = null;
	     Map<String, String> result = new HashMap<String, String>();
	     try {
	         client = new DefaultHttpClient();
	         HttpGet httpget = new HttpGet(requestUrl);
	         ResponseHandler<String> responseHandler = new BasicResponseHandler();
	         String response = client.execute(httpget, responseHandler);
	         JSONObject openidJson = JSONObject.parseObject(response);
	         String errcode = String.valueOf(openidJson.get("errcode"));
	         String errmsg = String.valueOf(openidJson.get("errmsg"));
	         String ticket = String.valueOf(openidJson.get("ticket"));
	         String expiresIn = String.valueOf(openidJson.get("expires_in"));
	         result.put("errcode", errcode);
	         result.put("errmsg", errmsg);
	         result.put("ticket", ticket);
	         result.put("expires_in", expiresIn);
	         //System.out.println(result);
	     } catch (Exception e) {
	         e.printStackTrace();
	     } finally {
	         client.getConnectionManager().shutdown();
	     }
	     return result;
	 }
	/**
	 * 获取AccessToken
	 **/
	public Map<String, String> getAccessToken(String appid, String appsecret) {

	    String requestUrl = GET_PAGE_ACCESS_TOKEN_URL.replace("APPID", appid).replace("SECRET", appsecret);

	    HttpClient client = null;
	    Map<String, String> result = new HashMap<String, String>();
	    String accessToken = null;
	    try {
	        client = new DefaultHttpClient();
	        HttpGet httpget = new HttpGet(requestUrl);
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        String response = client.execute(httpget, responseHandler);
	        JSONObject openidJson = JSONObject.parseObject(response);
	        accessToken = String.valueOf(openidJson.get("access_token"));
	        result.put("accessToken", accessToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        client.getConnectionManager().shutdown();
	    }
	    return result;
	}
	/**
	 * sha-1加密
	 * @param str
	 * @return
	 */
	public static String SHA1(String str) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
			// 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static void main(String[] args) {
		WXJsSdk d = new WXJsSdk();
		Map map1= d.getAccessToken("wx0beec6ea6bb0067d","891d1f6d611ecd8e73277ed1027810bb");
		Map map2=d.getJsapiTicket((String) map1.get("accessToken"));
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String noncestr = UUID.randomUUID().toString().replace("-", "");
		String url="";
		
		String str = "jsapi_ticket=" + map2.get("ticket") + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=http://sungong1987.gicp.net:27059" ;
		String signature = SHA1(str);
		System.out.println(str);
		System.out.println(signature);
	}
}
