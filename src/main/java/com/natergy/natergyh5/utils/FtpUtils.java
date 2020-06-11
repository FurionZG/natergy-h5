package com.natergy.natergyh5.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Ftp工具类
 * @author 杨枕戈
 */
@Component
public class FtpUtils {

    private static String ftpUrl="219.146.150.102";
    private static String ftpPort="60001";
    private static String ftpPassword="52nj1T2T";
    private static String ftpUser="Ni";

    public static FTPClient getFtpClient() throws Exception{
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8"); //GBK
        FTPClientConfig conf = new FTPClientConfig(); //FTPClientConfig.SYST_NT
        conf.setServerLanguageCode("zh");
        ftpClient.configure(conf);
        ftpClient.connect(ftpUrl, Integer.parseInt(ftpPort));
        ftpClient.login(ftpUser,ftpPassword);
        ftpClient.setDataTimeout(120000);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//		ftpClient.configure(config);
//		ftpClient.setControlEncoding("GBK");
        //2020.5.6添加中文编码问题

        return ftpClient;

    }

    @Test
    public void run() throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D://test.jpg"));
        FTPClient ftpClient = FtpUtils.getFtpClient();
        ftpClient.changeWorkingDirectory("natergy");
        ftpClient.enterLocalPassiveMode();
        String fileName = "杨枕戈" + "-" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "-" + (UUID.randomUUID().toString().replace("-", "")) + ".jpg";
        String path = new String(("./pic/" + fileName).getBytes(), "utf-8");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        System.out.println(ftpClient.storeFile(path,bis));
    }
}
