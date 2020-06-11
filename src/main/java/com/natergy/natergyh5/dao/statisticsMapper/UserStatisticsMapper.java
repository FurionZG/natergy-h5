package com.natergy.natergyh5.dao.statisticsMapper;


import com.natergy.natergyh5.entity.statisticsEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2018/5/5.
 */
@Mapper
@Repository
public interface UserStatisticsMapper {
    String selectByPrimaryKey(int cid);

    List<User> selectUsers(Map<String, Object> params);

}
