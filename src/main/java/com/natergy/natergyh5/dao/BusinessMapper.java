package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Business;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("select * from 销售出差登记 where 姓名=#{uname} order by Id desc limit 10")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "uname",column = "姓名"),
            @Result(property = "businessNo",column = "编号"),
            @Result(property = "startDate",column = "起始日期"),
            @Result(property = "endDate",column = "终止日期"),
            @Result(property = "time",column = "天数"),
            @Result(property = "visitCustomerCount",column = "拜访客户数"),
            @Result(property = "visitNewCustomerCount",column = "新户数"),
            @Result(property = "startMileage",column = "起始里程"),
            @Result(property = "startImage",column = "起始里程照片_附件"),
            @Result(property = "endMileage",column = "终止里程"),
            @Result(property = "endImage",column = "终止里程照片_附件"),
            @Result(property = "Mileage",column = "公里数"),
            @Result(property = "roadToll",column = "过路费"),
            @Result(property = "fuelCosts",column = "加油费"),
            @Result(property = "fuelVolume",column = "加油数（升）"),
            @Result(property = "oilConsumption",column = "油耗"),
            @Result(property = "ticket",column = "车票"),
            @Result(property = "specialSubsidies",column = "特补"),
            @Result(property = "specialSubsidiesDescription",column = "特补说明"),
            @Result(property = "mealAllowance",column = "餐补"),
            @Result(property = "accommodation",column = "住宿"),
            @Result(property = "totalCosts",column = "本次花费"),
            @Result(property = "customerFee",column = "客情费"),
            @Result(property = "trip",column = "行程"),
            @Result(property = "summary",column = "总结"),
            @Result(property = "proposal",column = "建议"),
    })
    List<Business> getBusinessByUser(String uname);

    @Update("update 销售出差登记 set 起始日期=#{startDate},终止日期=#{endDate},起始里程=#{startMileage},终止里程=#{endMileage},公里数=#{Mileage},过路费=#{roadToll},加油费=#{fuelCosts},加油数（升）=#{fuelVolume},车票=#{ticket},特补=#{specialSubsidies},特补说明=#{specialSubsidiesDescription},餐补=#{mealAllowance},住宿=#{accommodation},本次花费=#{totalCosts},客情费=#{customerFee},行程=#{trip},总结=#{summary},建议=#{proposal},起始里程照片_附件=#{startImage},终止里程照片_附件=#{endImage} where Id=#{Id}")
    Integer updateBusiness(Business business);

    @Select("select * from 销售出差登记 where 姓名=#{uname} order by Id desc limit #{limit},5")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "uname",column = "姓名"),
            @Result(property = "businessNo",column = "编号"),
            @Result(property = "startDate",column = "起始日期"),
            @Result(property = "endDate",column = "终止日期"),
            @Result(property = "time",column = "天数"),
            @Result(property = "visitCustomerCount",column = "拜访客户数"),
            @Result(property = "visitNewCustomerCount",column = "新户数"),
            @Result(property = "startMileage",column = "起始里程"),
            @Result(property = "startImage",column = "起始里程照片_附件"),
            @Result(property = "endMileage",column = "终止里程"),
            @Result(property = "endImage",column = "终止里程照片_附件"),
            @Result(property = "Mileage",column = "公里数"),
            @Result(property = "roadToll",column = "过路费"),
            @Result(property = "fuelCosts",column = "加油费"),
            @Result(property = "fuelVolume",column = "加油数（升）"),
            @Result(property = "oilConsumption",column = "油耗"),
            @Result(property = "ticket",column = "车票"),
            @Result(property = "specialSubsidies",column = "特补"),
            @Result(property = "specialSubsidiesDescription",column = "特补说明"),
            @Result(property = "mealAllowance",column = "餐补"),
            @Result(property = "accommodation",column = "住宿"),
            @Result(property = "totalCosts",column = "本次花费"),
            @Result(property = "customerFee",column = "客情费"),
            @Result(property = "trip",column = "行程"),
            @Result(property = "summary",column = "总结"),
            @Result(property = "proposal",column = "建议"),
    })
    List<Business> getBusinessByLimit(String uname, Integer limit);
}
