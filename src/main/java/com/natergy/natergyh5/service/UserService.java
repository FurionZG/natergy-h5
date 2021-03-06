package com.natergy.natergyh5.service;

import com.alibaba.fastjson.JSONObject;
import com.natergy.natergyh5.annotations.OperationLog;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用户模块业务层
 * @author 杨枕戈
 */
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

    /**
     * 检查用户名密码是否正确
     * @param user 用户对象
     * @return 返回用户名密码是否正确
     */
    public Integer checkUser(User user) {
        return userDao.checkUser(user);
    }

    /**
     * 通过微信code，使用HttpClient发起模拟请求，请求打开公众号的微信用户的openId，并验证数据库中是否有该openId
     * @param code 微信提供的code
     * @return 返回数据库中是否有该用户的openId
     */
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
        System.out.println("openId:"+openId);
        User user1 = userDao.selectUserByOpenidField(openId);
        User user2 = userDao.selectUserByOpenid(openId);
        if(null!=user1){
            return user1.getUname();
        }
        if(null!=user2){
            return user2.getUname();
        }
        return "0";


    }

    public List<String> getSalesman() {
        return userDao.getSalesman();
    }


    public String getOpenId(String code) throws IOException {
        String openId = "";
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
        return openId;
    }

    public Integer bindOpenId(String openId, String username) {
        return userDao.bindOpenId(openId,username);
    }

    public int checkBind(String openid,String username) {
        //openid已被绑定
        if(0!=userDao.checkOpenId(openid)){
            return -1;
        }
        //名字已被绑定
        if(null!=userDao.isBind("username")){
            return -2;
        }
        return 1;
    }


    public String getUsernameByOpenId(String code) throws IOException {
        String openId = getOpenId(code);
        return userDao.getUsernameByOpenId(openId);
    }
}
