package com.natergy.natergyh5.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.natergy.natergyh5.dao.OrderMapper;
import com.natergy.natergyh5.dao.UserMapper;
import com.natergy.natergyh5.entity.Deliver;
import com.natergy.natergyh5.entity.Entry;
import com.natergy.natergyh5.entity.User;
import com.natergy.natergyh5.entity.wxEntity.WXJsSdk;
import com.natergy.natergyh5.utils.HttpClientUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/** @Author 杨枕戈 @Date 2020-06-09 10:59 @Version 1.0 */
@Component
public class NatergySchedule {

  @Autowired private UserMapper userMapper;
  @Autowired private OrderMapper orderMapper;

  @Value("${natergy.appId}")
  private String appId;

  @Value("${natergy.appSecret}")
  private String appSecret;

  @Value("${DeliverTemplateMessage}")
  private String deliverTemplateMessage;

  @Value("${DeliverWxTemplateName}")
  private String deliverWxTemplateName;

  private static String TemplateMessage_Url =
      "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
  private Map<String, Integer> totalMap;

  /** 每日各种规格订单量与发货量推送 */
  @Scheduled(cron = "0 0 19 * * ?")
  public void DeliverWxTemplateMessage() throws IOException {
    WXJsSdk d = new WXJsSdk();
    // accessToken
    Map map1 = d.getAccessToken(appId, appSecret);
    String accessToken = (String) map1.get("accessToken");
    String postUrl = TemplateMessage_Url.replace("ACCESS_TOKEN", accessToken);

    List<Deliver> todayDeliverList = orderMapper.getDeliverToday(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
    Map<String, Integer> shippedMap = new HashMap<>();
    Map<String, Integer> notShippedMap = new HashMap<>();
    Map<String, Integer> totalMap = new HashMap<>();
    for (Deliver deliver : todayDeliverList) {
      totalMap.put(
          deliver.getSize(),
              totalMap.containsKey(deliver.getSize())
              ? totalMap.get(deliver.getSize()) + deliver.getWeight()
              : deliver.getWeight());
      if ("已发货".equals(deliver.getStatus()) || "已到货".equals(deliver.getStatus())) {
        shippedMap.put(
            deliver.getSize(),
            shippedMap.containsKey(deliver.getSize())
                ? shippedMap.get(deliver.getSize()) + deliver.getWeight()
                : deliver.getWeight());
      } else {
        notShippedMap.put(
            deliver.getSize(),
            notShippedMap.containsKey(deliver.getSize())
                ? notShippedMap.get(deliver.getSize()) + deliver.getWeight()
                : deliver.getWeight());
      }
    }
    LinkedList<String> receiveNameList =
        new LinkedList<>(Arrays.asList(deliverWxTemplateName.split("/")));
    receiveNameList.removeFirst();
    for (String u : receiveNameList) {
      JSONObject jsonObject = new JSONObject();

      User user = userMapper.selectUserByName(u);
      String openid = user.getOpenId();
      jsonObject = setDeliverMessageEntity(shippedMap, notShippedMap,totalMap, openid);
      String string = HttpClientUtils.sendPostJsonStr(postUrl, jsonObject.toJSONString());
      JSONObject result = JSON.parseObject(string);
      int errcodeTmp = result.getIntValue("errcode");
    }
  }

  private JSONObject setDeliverMessageEntity(
      Map<String, Integer> shippedMap, Map<String, Integer> notShippedMap, Map<String, Integer> totalMap,String openid) {

    String totalString = "";
    Integer totalShip =0;
    String noShippedString ="";
    Integer totalShipped=0;
    for(String key  : totalMap.keySet()){
      totalString+=key+"\t\t"+totalMap.get(key)+"Kg\r\n";
      totalShip+=totalMap.get(key);
    }
    for(String key  : notShippedMap.keySet()){
      noShippedString+=key+"\t\t"+notShippedMap.get(key)+"Kg\r\n";
      totalShipped+=notShippedMap.get(key);
    }
    totalString+="总计\t\t"+totalShip+"Kg";
    noShippedString+="总计\t\t"+totalShipped+"Kg";

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("touser", openid); // openid
    jsonObject.put("template_id", deliverTemplateMessage);
    jsonObject.put("url", "");
    JSONObject data = new JSONObject();
    JSONObject first = new JSONObject();
    first.put("value", "每日发货信息");
    first.put("color", "#E30423");
    JSONObject keyword1 = new JSONObject();
    keyword1.put("value", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
    keyword1.put("color", "#173177");
    JSONObject keyword2 = new JSONObject();
    keyword2.put("value", "");
    keyword2.put("color", "#173177");
    JSONObject keyword3 = new JSONObject();
    keyword3.put("value", "分子筛");
    keyword3.put("color", "#173177");
    JSONObject keyword4 = new JSONObject();
    keyword4.put("value", totalString);
    keyword4.put("color", "#173177");
    JSONObject keyword5 = new JSONObject();
    keyword5.put("value", noShippedString);
    keyword5.put("color", "#173177");

    data.put("first", first);
    data.put("keyword1", keyword1);
    data.put("keyword2", keyword2);
    data.put("keyword3", keyword3);
    data.put("keyword4", keyword4);
    data.put("keyword5", keyword5);


    jsonObject.put("data", data);

    return jsonObject;
  }
}
