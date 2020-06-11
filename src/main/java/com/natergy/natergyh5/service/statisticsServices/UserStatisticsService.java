package com.natergy.natergyh5.service.statisticsServices;


import com.natergy.natergyh5.dao.statisticsMapper.UserStatisticsMapper;
import com.natergy.natergyh5.entity.statisticsEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Created by wj on 2018/5/5.
 */
@Service
public class UserStatisticsService implements Serializable {
    @Autowired
    private UserStatisticsMapper userStatisticsMapper;

    public String select(int cid) {
        return userStatisticsMapper.selectByPrimaryKey(cid);
    }

    public List<User> selectUsers(Map<String, Object> params) {
        List<User> users = userStatisticsMapper.selectUsers(params);
        return users;
    }
}
