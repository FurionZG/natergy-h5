package com.natergy.natergyh5.service;

import com.alibaba.fastjson.JSONObject;
import com.natergy.natergyh5.dao.UserMapper;
import com.natergy.natergyh5.entity.User;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service

public class UserService {
    @Autowired
    private UserMapper userDao;
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;

    public Integer checkUser(User user) {
        return userDao.checkUser(user);
    }

    public String checkWxOpenId(String code) throws IOException {
        String openId=null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        HttpPost post = new HttpPost(url);

        List<NameValuePair> postData = new LinkedList();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue());
            postData.add(param);
        }
        post.setEntity(new UrlEncodedFormEntity(postData, "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(post);
        httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject result=JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            openId= (String) result.get("openid");
        }
        User user = userDao.selectUserByOpenid(openId);
        if(null!=user){
            return user.getUname();
        }else {
            return "0";
        }

    }
}
