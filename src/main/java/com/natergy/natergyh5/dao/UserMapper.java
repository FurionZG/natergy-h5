package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("Select count(*) from 用户 where 用户=#{user.uname} and 密码=#{user.pwd}")
    Integer checkUser(@Param("user") User u);

    @Select("select 用户编号 from 用户 where 用户=#{uname}")
    String getUserNo(String uname);
}
