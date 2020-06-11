package com.natergy.natergyh5.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 王杰开发
 */
public class TimeUtil {


    public static String getDateTime(String time) {
        // 按指定模式在字符串查找
//        String line = "2020-04-27 16:39";
        String pattern = "(\\d+)-(\\d+)-(\\d+)\\s+(\\d+):(\\d+)?";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(time);
        if (m.find()) {
            //        2019年09月21日09时50分21秒
            StringBuffer datebuffer = new StringBuffer();
            datebuffer.append(m.group(1));
            datebuffer.append("年");
            datebuffer.append(m.group(2));
            datebuffer.append("月");
            datebuffer.append(m.group(3));
            datebuffer.append("日");
            datebuffer.append(m.group(4));
            datebuffer.append("时");
            datebuffer.append(m.group(5));
            datebuffer.append("分");
            datebuffer.append("0");
            datebuffer.append("秒");
            return datebuffer.toString();
        } else {
            return time;
        }

    }


    public static String getEngDateTime(String time) {
        // 按指定模式在字符串查找
//        String line = "2020-04-27 16:39";
//        2017年9月13日10时21分40秒
        String pattern = "(\\d+)年(\\d+)月(\\d+)日(\\d+)时(\\d+)分(\\d+)秒";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(time);
        if (m.find()) {
            //        2019年09月21日09时50分21秒
            StringBuffer datebuffer = new StringBuffer();
            datebuffer.append(m.group(1));
            datebuffer.append("-");
            datebuffer.append(m.group(2));
            datebuffer.append("-");
            datebuffer.append(m.group(3));
//            datebuffer.append(" ");
//            datebuffer.append(m.group(4));
//            datebuffer.append(":");
//            datebuffer.append(m.group(5));
//            datebuffer.append(":");
//            datebuffer.append(m.group(6));
            return datebuffer.toString();
        } else {
            return time;
        }

    }

    public static String currentTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date date = new Date(System.currentTimeMillis());
        String itemtime = formatter.format(date);
        System.out.println(itemtime);
        return itemtime;
    }

    public static void main(String[] args) {
//        String province = "山东省";
//        int index = province.indexOf("省");
//        System.out.println(province.substring(0, index));
//
//        System.out.println(getEngDateTime("2017年9月13日10时21分40秒"));
//        BigDecimal a = new BigDecimal(45);
//        BigDecimal b = new BigDecimal(46);
//
//        System.out.println(a.compareTo(b));
//        currentTime();
        System.out.println(getDateTime("2020-04-27 16:39"));;
    }
}
