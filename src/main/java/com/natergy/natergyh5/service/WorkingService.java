package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.OptionsMapper;
import com.natergy.natergyh5.dao.WorkingMapper;
import com.natergy.natergyh5.entity.Option;
import com.natergy.natergyh5.entity.Visit;
import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.entity.Working;
import com.natergy.natergyh5.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkingService {
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;
    @Value("${SalesExecutive}")
    String salesExecutive ;
    @Autowired
    private WorkingMapper workingDao;
    @Autowired
    private OptionsMapper optionsDao;


    public List<Working> getWorkings(String uname) {
        List<Working> resultList = workingDao.getWorkings(uname);
        for (Working working : resultList) {
            List<String> imgesList = new ArrayList<>();
            String imgStr = working.getOptions();
            if (null != imgStr) {
                String[] images = imgStr.split("/");
                //不要第一个值，因为第一个值是""
                for (int i = 1; i < images.length; i++) {
                    String imageUrl = optionsDao.queryOption(images[i]);
                    imgesList.add(imageUrl);
                }
            }
            working.setImages(imgesList);
        }
        return resultList;
    }

    @Transactional
    public Integer saveWorking(Working working) throws Exception {
        saveWorkingOptions(working);
        return workingDao.saveWorking(working);
    }

    public Integer updateWorking(Working working) throws Exception {
        saveWorkingOptions(working);
        System.out.println(working);
        return workingDao.updateWorking(working);
    }

    private void saveWorkingOptions(Working working) throws Exception {
        WXJsSdk d = new WXJsSdk();
        Map map1 = d.getAccessToken(appId, appSecret);
        String accessToken = (String) map1.get("accessToken");
        List<Option> options = new ArrayList<>();
        List<String> list = working.getImages();
        if ("" != list.get(0)) {
            for (String media_id : list) {
                String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
                requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", media_id);
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
                String fileName = working.getUser() + "-" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "-" + (UUID.randomUUID().toString().replace("-", "")) + ".jpg";
                String path = new String(("./pic/" + fileName).getBytes("gbk"), "iso-8859-1");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //System.out.println(ftpClient.storeFile(path,bis));
                if (ftpClient.storeFile(path, bis)) {
                    Option option = new Option();
                    option.setName(fileName);
                    option.setPos("销售工作汇报");
                    options.add(option);
                } else {
                    throw new Exception("照片保存错误");
                }
            }
            optionsDao.saveOptions(options);
            StringBuffer sb = new StringBuffer();
            for (Option option : options) {
                sb.append("/");
                sb.append(option.getId());
            }
            working.setOptions(sb.toString());
        }
        working.setReader(salesExecutive);
    }

    public List<Working> reloadWorkings(Integer limit, String uname) {
        return workingDao.reloadWorkings(limit,uname);
    }

    public Set<String> getWorkingsName(String uname) {
        Set<String>resultList = new HashSet<>(workingDao.getWorkingsName(uname));
        return resultList;
    }

    public List<Working> getWorkingsByName(String uname,String name) {
        return workingDao.getWorkingsByName(uname,name,salesExecutive);
    }

    public List<Working> reloadWorkingsByName(Integer limit, String uname,String name) {
        return workingDao.reloadWorkingsByName(limit,uname,name);
    }
}
