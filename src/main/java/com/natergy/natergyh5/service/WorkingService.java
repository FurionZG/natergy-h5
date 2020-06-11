package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.OptionsMapper;
import com.natergy.natergyh5.dao.WorkingMapper;
import com.natergy.natergyh5.entity.Option;
import com.natergy.natergyh5.entity.wxEntity.WXJsSdk;
import com.natergy.natergyh5.entity.Working;
import com.natergy.natergyh5.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售工作进程模块业务层
 * @author 杨枕戈
 */
@Service
public class WorkingService {
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;
    @Value("${SalesExecutive}")
    private String salesExecutive ;
    @Autowired
    private WorkingMapper workingDao;
    @Autowired
    private OptionsMapper optionsDao;


    /**
     * 查询登录用户的最后10条工作进程，并将图片附件的文件名注入到对应的工作进程中
     * @param uname 登录用户的用户名
     * @return 返回登录用户的最后10条工作进程
     */
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

    /**
     * 保存工作进程
     * @param working 工作进程对象
     * @return 返回是否保存成功
     */
    @Transactional
    public Integer saveWorking(Working working) throws Exception {
        saveWorkingOptions(working);
        return workingDao.saveWorking(working);
    }

    /**
     * 更新工作进程
     * @param working 工作进程对象
     * @return 返回是否更新成功
     */
    public Integer updateWorking(Working working) throws Exception {
        saveWorkingOptions(working);
        System.out.println(working);
        return workingDao.updateWorking(working);
    }

    /**
     * 保存工作进程图片附件，将图片附件通过微信图片唯一标识medis_id取回后，直接通过I/O流保存到Ftp服务器
     * @param working 工作进程对象
     */
    private void saveWorkingOptions(Working working) throws Exception {
        WXJsSdk d = new WXJsSdk();
        Map map1 = d.getAccessToken(appId, appSecret);
        String accessToken = (String) map1.get("accessToken");
        List<Option> options = new ArrayList<>();
        List<String> list = working.getImages();
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
                String fileName = working.getUser() + "-" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "-" + (UUID.randomUUID().toString().replace("-", "")) + ".jpg";
                String path = new String(("./pic/" + fileName).getBytes(), "utf-8");
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

    /**
     * 查询更多登录用户的工作进程
     * @param limit 页面显示的工作进程数量
     * @param uname 登录用户的用户名
     * @return 返回从limit开始之后的5条工作进程记录
     */
    public List<Working> reloadWorkings(Integer limit, String uname) {
        return workingDao.reloadWorkings(limit,uname);
    }

    /**
     * 查询所有有工作进程的汇报人Set集
     * @param uname 登录用户的用户名
     * @return 返回所有有所有进程的汇报人Set集
     */
    public Set<String> getWorkingsName(String uname) {
        Set<String>resultList = new HashSet<>(workingDao.getWorkingsName(uname));
        return resultList;
    }

    /**
     * 查询某个汇报人的工作汇报，此功能只对销售主管开放
     * @param uname 登录用户的用户名
     * @param name 汇报人姓名
     * @return 返回某个汇报人的工作汇报列表
     */
    public List<Working> getWorkingsByName(String uname,String name) {
        return workingDao.getWorkingsByName(uname,name,salesExecutive);
    }

    /**
     * 查询某个汇报人的更多的工作进程
     * @param limit 页面显示的工作进程条数
     * @param uname 登录用户的用户名
     * @param name 汇报人姓名
     * @return 返回某个汇报人的limit之后5条的工作进程
     */
    public List<Working> reloadWorkingsByName(Integer limit, String uname,String name) {
        return workingDao.reloadWorkingsByName(limit,uname,name);
    }
}
