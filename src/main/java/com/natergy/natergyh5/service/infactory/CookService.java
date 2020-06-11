package com.natergy.natergyh5.service.infactory;

import com.natergy.natergyh5.dao.infactory.CookMapper;
import com.natergy.natergyh5.entity.infactory.Cook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-02-23 11:19
 * @Version 1.0
 * 
 */
@Service
public class CookService {
    @Autowired
    private CookMapper cookMapper;

    public Cook queryYesterdayCook(String uname) {
        return cookMapper.queryYesterdayCook(uname);
    }

    public Integer addCook(Cook cook) {
        return cookMapper.addCook(cook);
    }

    public List<Cook> queryCookList(String uname) {
        return cookMapper.queryCookList(uname);
    }

    public List<Cook> queryCookListLimit(String uname, Integer limit) {
        return cookMapper.queryCookListLimit(uname,limit);
    }


    public Integer updateCook(Cook cook) {
        return cookMapper.updateCook(cook);
    }

    public String countCook(String date, String workspace, String eat) {
        List<Cook> cookList  = cookMapper.countCook(date,workspace);
        if(eat.equals("早餐")){
            Integer count  = 0;
            for(Cook cook :cookList){
                if("是".equals(cook.getBreakfast())){
                    count++;
                }
            }
            return date+"\n"+workspace+"\n 早餐"+count+"份";
        }
        if(eat.equals("午餐")){
            Integer count  = 0;
            Integer cMantou = 0;
            for(Cook cook :cookList){
                if("是".equals(cook.getLunch())){
                    count++;
                    cMantou+=Integer.parseInt(cook.getlMantou().substring(0,1));
                }
            }
            return date+"\n"+workspace+"\n 午餐"+count+"份"+"\n 馒头"+cMantou+"个";
        }
        if(eat.equals("晚餐")){
            Integer count  = 0;
            Integer cMantou = 0;
            for(Cook cook :cookList){
                if("是".equals(cook.getSupper())){
                    count++;
                    cMantou+=Integer.parseInt(cook.getsMantou().substring(0,1));
                }
            }
            return date+"\n"+workspace+"\n 晚餐"+count+"份"+"\n 馒头"+cMantou+"个";
        }
        if(eat.equals("夜餐")){
            Integer count  = 0;
            Integer cMantou = 0;
            for(Cook cook :cookList){
                if("是".equals(cook.getNightSnack())){
                    count++;
                    cMantou+=Integer.parseInt(cook.getnMantou().substring(0,1));
                }
            }
            return date+"\n"+workspace+"\n 夜餐"+count+"份"+"\n 馒头"+cMantou+"个";
        }
        return null;
    }
}
