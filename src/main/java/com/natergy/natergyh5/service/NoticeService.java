package com.natergy.natergyh5.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.natergy.natergyh5.dao.NoticeMapper;
import com.natergy.natergyh5.dao.UserMapper;
import com.natergy.natergyh5.entity.Notice;
import com.natergy.natergyh5.entity.User;
import com.natergy.natergyh5.entity.wxEntity.WXJsSdk;
import com.natergy.natergyh5.entity.wxEntity.WxToken;
import com.natergy.natergyh5.utils.HttpClientUtils;
import com.natergy.natergyh5.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 杨枕戈
 * @Date 2020-03-07 8:41
 * @Version 1.0
 */
@Service
public  class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${SalesTemplateMessage}")
    private String SalesTemplateMessage;
    @Value("${SalesForwardUrl}")
    private String url;
    @Autowired
    private UserMapper userMapper;

    private static String TemplateMessage_Url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public List<Notice> queryAllNotices() {
        return noticeMapper.queryAllNotices();
    }

    public List<String> querySalesmanList() {
        return noticeMapper.querySalesmanList();
    }

    public String saveNotice(Notice notice) throws IOException {
        noticeMapper.saveNotice(notice);
        WXJsSdk d = new WXJsSdk();
        //accessToken
        notice.setToStaff(notice.getToStaff() + "/杨枕戈");
        Map map1 = d.getAccessToken(appId, appSecret);
        String accessToken = (String) map1.get("accessToken");
        String postUrl = TemplateMessage_Url.replace("ACCESS_TOKEN", accessToken);
        LinkedList<String> toStaffList = new LinkedList<>(Arrays.asList(notice.getToStaff().split("/")));
        toStaffList.removeFirst();
        JSONObject jsonObject = new JSONObject();
        int errcode = 0;
        for (String u:toStaffList) {
            User user = userMapper.selectUserByName(u);
            String openid = user.getOpenId();
            jsonObject = setMessageEntity(notice, openid);
            String string = HttpClientUtils.sendPostJsonStr(postUrl, jsonObject.toJSONString());
            JSONObject result = JSON.parseObject(string);
            int errcodeTmp = result.getIntValue("errcode");
            errcode += errcodeTmp;
            if (0 != errcodeTmp) {
                String strTmp = noticeMapper.getError(notice.getId());
                if(!strTmp.contains(u)){
                    strTmp+="/"+u;
                }
                noticeMapper.updateError(strTmp,notice.getId());
            }
        }
        if (errcode == 0) {
            // 发送成功
            System.out.println("发送成功");
        } else {
            // 发送失败
            System.out.println("部分发送成功");
        }
        return "ok";

    }

    public JSONObject setMessageEntity(Notice notice, String openid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openid);   // openid
        jsonObject.put("template_id", SalesTemplateMessage);
        jsonObject.put("url", url.replace("XXXXXX","http://www.iluluya.com/natergy-h5/notice/viewInit/"+notice.getId()));

        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", "销售通知");
        first.put("color", "#E30423");
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", notice.getNoticeContent());
        keyword1.put("color", "#173177");
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", notice.getPublishTime());
        keyword2.put("color", "#173177");
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", notice.getPublisher());
        keyword3.put("color", "#173177");
        JSONObject remark = new JSONObject();
        remark.put("value", "请及时处理");
        remark.put("color", "#173177");

        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("remark", remark);

        jsonObject.put("data", data);

        return jsonObject;
    }

    public String getNoticeContentById(String id) {
        return noticeMapper.getNoticeContentById(id);
    }

    public void saveRead(String uname, String id) {
        String strTmp = noticeMapper.queryRead(id);
        if(!strTmp.contains(uname)){
            strTmp=strTmp+"/"+uname;
        }
        noticeMapper.saveRead(strTmp,id);
    }

    public String getSendError(String id) {
        return noticeMapper.getSendError(id);
    }

    public String getToStaff(String id) {
        return noticeMapper.getToStaff(id);
    }

    public List<Notice> reloadNotices(Integer limit) {
        return noticeMapper.reloadNotices(limit);
    }
}
