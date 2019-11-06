package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 用户Dao
 * @author 杨枕戈
 */
@Repository
@Mapper
public interface UserMapper {

    @Select("Select count(*) from 用户 where 用户=#{user.uname} and 密码=#{user.pwd}")
    Integer checkUser(@Param("user") User u);

    @Select("select 用户编号 from 用户 where 用户=#{uname}")
    String getUserNo(String uname);

    @Select("Select 用户,密码 from 用户 where 微信关联=#{openId}")
    @Results({
            @Result(column = "用户",property ="uname")
    })
    User selectUserByOpenid(String openId);
}
