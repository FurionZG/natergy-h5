package com.natergy.natergyh5.utils;

import com.natergy.natergyh5.dao.OptionsMapper;
import com.natergy.natergyh5.entity.Option;
import com.natergy.natergyh5.entity.wxEntity.WXJsSdk;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 附件处理工具类
 * @author 杨枕戈
 */
@Component
public class SaveImageToServer {
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;
    @Autowired
    private OptionsMapper optionsDao;

    /**
     * 该方法将微信服务器上的图片通过I/O流传输到FTP服务器上
     * @param mediaId 微信服务器图片的唯一id
     * @param user 用户名
     * @param from 附件表中的位置字段
     * @return 返回附件表中的Id字段，多条记录用"/"隔开
     */
    public String saveImageToServer(String mediaId, String user, String from) throws Exception {
        WXJsSdk d = new WXJsSdk();
        Map map1 = d.getAccessToken(appId, appSecret);
        String accessToken = (String) map1.get("accessToken");
        List<Option> options = new ArrayList<>();
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        //图片缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        //获取FtpClient
        FTPClient ftpClient = FtpUtils.getFtpClient();
        ftpClient.changeWorkingDirectory("natergy");
        ftpClient.enterLocalPassiveMode();
        String fileName = user + "-" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "-" + (UUID.randomUUID().toString().replace("-", "")) + ".jpg";
        String path = new String(("./pic/" + fileName).getBytes(), "utf-8");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        if (ftpClient.storeFile(path, bis)) {
            Option option = new Option();
            option.setName(fileName);
            option.setPos(from);
            options.add(option);
        } else {
            throw new Exception("照片保存错误");
        }
        optionsDao.saveOptions(options);
        StringBuffer sb = new StringBuffer();
        for (Option option : options) {
            sb.append("/");
            sb.append(option.getId());
        }
        return sb.toString();
    }
}
