package com.natergy.natergyh5.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FtpUtils {


    private static String ftpUrl="219.146.150.102";
    private static String ftpPort="60001";
    private static String ftpPassword="52nj1T2T";
    private static String ftpUser="Ni";

    public static FTPClient getFtpClient() throws Exception{
        FTPClient ftpClient = new FTPClient();
        //ftpClient.setControlEncoding("utf-8");
        ftpClient.connect(ftpUrl, Integer.parseInt(ftpPort));
        ftpClient.login(ftpUser,ftpPassword);
        ftpClient.setDataTimeout(120000);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置为二进制文件
//        int replyCode = ftpClient.getReplyCode();
//        if (!FTPReply.isPositiveCompletion(replyCode)) {
//            ftpClient.disconnect();
//            System.out.println("FTP连接失败");
//        }else {
//            System.out.println("FTP连接成功");
//        }
        return ftpClient;

    }
}
