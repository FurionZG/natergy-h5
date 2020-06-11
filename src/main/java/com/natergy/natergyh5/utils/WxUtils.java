package com.natergy.natergyh5.utils;

import com.natergy.natergyh5.entity.wxEntity.WXJsSdk;
import com.natergy.natergyh5.entity.wxEntity.WxToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class WxUtils {
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;

    /**
     * 生成微信jssdk需要的字段信息：signature,noncestr,timestamp,appId
     * @param website 需要调用微信jssdk的页面地址（除顶级域名），例：http://www.baidu.com/123/456，这里website为/123/456
     * @return 返回微信Token对象，包含注入jssdk所需的字段信息
     */
    public WxToken getWxToken(String website){
        WxToken wxToken = new WxToken();
        WXJsSdk d = new WXJsSdk();
        //accessToken
        Map map1= d.getAccessToken(appId,appSecret);
        //ticket
        Map map2= d.getJsapiTicket((String) map1.get("accessToken"));
        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        //uuid
        String noncestr = UUID.randomUUID().toString().replace("-", "");
        //拼接调用微信jssdk功能的url
        String url="http://"+host+website;
        //拼接ticket，uuid，时间戳和url
        String str = "jsapi_ticket=" + map2.get("ticket") + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        //对拼接的字符串进行加密算法
        String signature = WXJsSdk.SHA1(str);
        wxToken.setSignature(signature);
        wxToken.setAppId(appId);
        wxToken.setNoncestr(noncestr);
        wxToken.setTimestamp(timestamp);
        return wxToken;
    }



}
