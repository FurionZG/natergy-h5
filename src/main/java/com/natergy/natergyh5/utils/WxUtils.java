package com.natergy.natergyh5.utils;

import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.entity.WxToken;
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

    public WxToken getWxToken(String website){
        WxToken wxToken = new WxToken();
        WXJsSdk d= new WXJsSdk();
        Map map1= d.getAccessToken(appId,appSecret);
        Map map2=d.getJsapiTicket((String) map1.get("accessToken"));
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String noncestr = UUID.randomUUID().toString().replace("-", "");
        String url="http://"+host+website;
        String str = "jsapi_ticket=" + map2.get("ticket") + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = d.SHA1(str);

        wxToken.setSignature(signature);
        wxToken.setAppId(appId);
        wxToken.setNoncestr(noncestr);
        wxToken.setTimestamp(timestamp);
        return wxToken;
    }
}
