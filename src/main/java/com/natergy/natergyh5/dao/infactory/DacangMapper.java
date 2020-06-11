package com.natergy.natergyh5.dao.infactory;

import com.natergy.natergyh5.entity.infactory.Dacang;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DacangMapper {

    @Select("select * from 大仓列表 where 大仓编号=#{no}")
    @Results({
            @Result(column = "状态",property = "status"),
            @Result(column = "大仓编号",property = "no"),
            @Result(column = "操作时间",property = "operateTime")
    })
    Dacang selectByNo(String no);

    @Update("update 大仓列表 set 状态=#{status} , 操作时间=#{operateTime} where 大仓编号=#{no}")
    Integer update(Dacang dacang);
}
