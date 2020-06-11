package com.natergy.natergyh5.service.infactory;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.dao.infactory.DacangMapper;
import com.natergy.natergyh5.entity.infactory.Dacang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-04-01 16:12
 * @Version 1.0
 * 
 */

@Service
public class DacangService {

    @Autowired
    private DacangMapper dacangMapper;

    public String transform(String no) {
        Dacang dacang =dacangMapper.selectByNo(no);
        dacang.setOperateTime(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()));
        if("流化床".equals(dacang.getStatus())){
            dacang.setStatus("加固");
            Integer result=dacangMapper.update(dacang);
            return JSON.toJSONString(result);
        }
        if("加固".equals(dacang.getStatus())){
            dacang.setStatus("流化床");
            Integer result= dacangMapper.update(dacang);
            return JSON.toJSONString(result);
        }
        return null;
    }
}
