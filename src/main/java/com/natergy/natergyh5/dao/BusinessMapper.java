package com.natergy.natergyh5.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BusinessMapper {

    @Select("select count(*) from 销售出差登记 where 姓名=#{uname} and 状态='出差中'")
    Integer getOnBusiness(String uname);

    @Insert("insert into 销售出差登记(状态,姓名,编号,起始日期) values('出差中',#{uname},#{businessNo},#{nowTime})")
    Integer setBusinessBegin(String uname, String nowTime,String businessNo);

    @Update("update 销售出差登记 set 终止日期=#{nowTime},状态='已结束' where 姓名=#{uname} and 状态='出差中'")
    Integer setBusinessEnd(String uname,String nowTime);

    @Select("select count(*) from 销售出差登记 where 姓名=#{uname} and 编号 like CONCAT('%',#{nowYear},'%')")
    String getYearCount(String uname,String nowYear);

    @Select("select 起始日期 from 销售出差登记 where 姓名=#{uname} and 状态='出差中'")
    String getbusinessStartTime(String uname);

    @Select("select 编号 from 销售出差登记 where 姓名=#{uname} and 状态='出差中'")
    String getbusinessNo(String uname);
}
