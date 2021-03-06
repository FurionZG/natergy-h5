package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.CustomerMapper;
import com.natergy.natergyh5.dao.FollowUpMapper;
import com.natergy.natergyh5.dao.OptionsMapper;
import com.natergy.natergyh5.entity.FollowUp;
import com.natergy.natergyh5.entity.Option;
import com.natergy.natergyh5.entity.ResultOfAddress;
import com.natergy.natergyh5.entity.wxEntity.WXJsSdk;
import com.natergy.natergyh5.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 地产跟进业务层
 * @author 杨枕戈
 */
@Service
public class FollowUpService {

    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;
    @Value("${SalesExecutive}")
    String salesExecutive;
    @Autowired
    private FollowUpMapper followUpDao;
    @Autowired
    private CustomerMapper customerDao;
    @Autowired
    private OptionsMapper optionsDao;

    /**
     * 获取登录用户的最后10条地产跟进记录，同时将每条记录的图片附件文件名注入对应的地产跟进对象
     * @param user 登录用户的用户名
     * @return 该用户最后10条地产跟进记录
     */
    public List<FollowUp> getFollowUpByUser(String user) {
        List<FollowUp> resultList = followUpDao.getFollowUpByUser(user);
        for (FollowUp followUp : resultList) {
            List<String> imgesList = new ArrayList<>();
            if (null != followUp.getImgStr()) {
                String imgStr = followUp.getImgStr();
                String[] images = imgStr.split("/");
                //不要第一个值，因为第一个值是""
                for (int i = 1; i < images.length; i++) {
                    String imageUrl = optionsDao.queryOption(images[i]);
                    imgesList.add(imageUrl);
                }
            }
            followUp.setImages(imgesList);
        }
        return resultList;
    }

    /**
     * 获取业务经理为登录用户的客户名称
     * @param uname 登录用户的用户名
     * @return 返回业务经理为登录用户的客户列表
     */
    public List<String> getAllCompanys(String uname) {
        return customerDao.getCustomersByUser(uname);
    }

    /**
     * 获取某个公司的地址信息
     * @param companyName 公司名
     * @param uname 登录用户的用户名
     * @return 返回被查询公司的地址信息
     */
    public ResultOfAddress getAddressInfo(String companyName, String uname) {
        ResultOfAddress result = customerDao.getAddress(companyName, uname);
        result.setCompanyName(companyName);
        return result;
    }

    /**
     * 保存地产跟进，包括四部分
     * 1.将保存在微信服务器上的图片通过serverID取下来
     * 2.将该流转存到ftp服务器上，返回保存地址
     * 3.保存图片名到附件表和位置，返回id
     * 4.保存地产跟进信息
     * 以上四条作为一个事务
     *
     * @param followUp 地产跟进对象
     * @return 是否保存成功
     */
    public Integer saveFollowUp(FollowUp followUp) throws Exception {
        WXJsSdk d = new WXJsSdk();
        Map map1 = d.getAccessToken(appId, appSecret);
        String accessToken = (String) map1.get("accessToken");
        List<Option> options = new ArrayList<>();
        List<String> list = followUp.getImages();
        if ("" != list.get(0)) {
            for (String mediaId : list) {
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
                String fileName = followUp.getUser() + "-" + followUp.getDate() + "-" + (UUID.randomUUID().toString().replace("-", "")) + ".jpg";
                String path = new String(("./pic/" + fileName).getBytes(), "utf-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //System.out.println(ftpClient.storeFile(path,bis));
                if (ftpClient.storeFile(path, bis)) {
                    Option option = new Option();
                    option.setName(fileName);
                    option.setPos("地产业务跟进");
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
            followUp.setImgStr(sb.toString());
        }
        return followUpDao.saveFollowUp(followUp);
    }

    /**
     * 重新查询当前登录用户的地产跟进记录
     * @param uname 登录用户的用户名
     * @return 返回当前登录用户的最后10条地产跟进记录
     */
    public List<FollowUp> refreshFollowUp(String uname) {
        List<FollowUp> resultList = followUpDao.getFollowUpByUser(uname);
        for (FollowUp followUp : resultList) {
            List<String> imgesList = new ArrayList<>();
            if (null != followUp.getImgStr()) {
                String imgStr = followUp.getImgStr();
                String[] images = imgStr.split("/");
                //不要第一个值，因为第一个值是""
                for (int i = 1; i < images.length; i++) {
                    String imageUrl = optionsDao.queryOption(images[i]);
                    imgesList.add(imageUrl);
                }
            }
            followUp.setImages(imgesList);
        }
        return resultList;
    }

    /**
     * 更新地产跟进记录
     * @param followUp 地产跟进对象
     * @param uname 当前登录用户的用户名
     * @return 返回是否更新成功
     */
    public Integer updateFollowUp(FollowUp followUp, String uname) {
        return followUpDao.updateFollowUp(followUp, uname);
    }

    /**
     * 测试方法
     */
    @Test
    public void run() throws Exception {
        FTPClient ftpClient = FtpUtils.getFtpClient();
        System.out.println(ftpClient.changeWorkingDirectory("natergy"));

        ftpClient.enterLocalPassiveMode();
        String removePath = new String("./pic/张伟1-2019年.txt".getBytes("gbk"), "iso-8859-1");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

        System.out.println(ftpClient.storeFile(removePath, new FileInputStream(new File("E:\\1.txt"))));
        if (ftpClient != null) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     * 查询更多的登录用户的地产跟进记录
     * @param uname 登录用户的用户名
     * @param limit 页面山显示的跟进记录条数
     * @return 返回从limit开始之后的5条地产跟进记录
     */
    public List<FollowUp> reloadFolloUp(String uname, Integer limit) {
        List<FollowUp> resultList = followUpDao.reloadFollowUp(limit, uname);
        for (FollowUp followUp : resultList) {
            List<String> imgesList = new ArrayList<>();
            if (null != followUp.getImgStr()) {
                String imgStr = followUp.getImgStr();
                String[] images = imgStr.split("/");
                //不要第一个值，因为第一个值是""
                for (int i = 1; i < images.length; i++) {
                    String imageUrl = optionsDao.queryOption(images[i]);
                    imgesList.add(imageUrl);
                }
            }
            followUp.setImages(imgesList);
        }
        return resultList;
    }

    public List<FollowUp> getFollowUpInfoBySalesman(String salesmanName) {
        List<FollowUp> resultList = followUpDao.getFollowUpByUser(salesmanName);
        for (FollowUp followUp : resultList) {
            List<String> imgesList = new ArrayList<>();
            if (null != followUp.getImgStr()) {
                String imgStr = followUp.getImgStr();
                String[] images = imgStr.split("/");
                //不要第一个值，因为第一个值是""
                for (int i = 1; i < images.length; i++) {
                    String imageUrl = optionsDao.queryOption(images[i]);
                    imgesList.add(imageUrl);
                }
            }
            followUp.setImages(imgesList);
        }
        return resultList;
    }
}
