package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("Select 用户,密码 from 用户 where openid=#{openId}")
    @Results({
            @Result(column = "用户",property ="uname")
    })
    User selectUserByOpenidField(String openId);

    @Select("select 用户 from 用户 where 职位='国内业务经理' and 部门!='离职人员' ")
    List<String> getSalesman();

    @Update("update 用户 set openid = #{openId} where 用户 =#{username} and 部门!='离职人员' ")
    int bindOpenId(String openId, String username);

    @Select("select openid from 用户 where 用户=#{username} and 部门 != '离职人员'")
    String isBind(String username);

    @Select("select count(*) from 用户 where openid=#{openId} and 部门 != '离职人员'")
    int checkOpenId(String openId);

    @Select("select * from 用户 where 用户=#{name} and 部门 != '离职人员' ")
    User selectUserByName(String name);

    @Select("select 用户 from 用户 where openid =#{openId}")
    String getUsernameByOpenId(String openId);
}
