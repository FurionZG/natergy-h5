package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Visit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface VisitMapper {

    @Insert("insert into 销售拜访(状态,客户经理,时间,拜访记录,客户编码,客户名称,客户资料Id,地址,创建时间,附件,供应商或干燥剂类型,现用单价,省份,城市,地区,街道,经度,纬度,出差编号) values('草稿',#{user},#{date},#{record},#{customerNo},#{customerName},#{customerId},#{address},#{date},#{imgStr},#{productBrand},#{price},#{province},#{city},#{district},#{street},#{longitude},#{latitude},#{businessNo})")
    Integer saveBusiness(Visit visit);

    @Update("update 销售客户资料 set 干燥剂类型=#{productType},玻璃企业规模=#{consumption},客户类别=#{customerType},联系人姓名=#{contacts_1},联系人2姓名=#{contacts_2},联系人3姓名=#{contacts_3},联系人手机=#{tel_1},联系人2手机=#{tel_2},联系人3手机=#{tel_3} where Id=#{customerId}")
    Integer updateCustomer(Visit visit);

    @Select("select 客户编码 from 销售客户资料 where 客户名称=#{customerName} and 业务经理 like CONCAT('%',#{user},'%')")
    String selectCustomerNo(String customerName,String user);

    @Select("select Id from 销售客户资料 where 客户名称=#{customerName} and 业务经理 like CONCAT('%',#{user},'%')")
    String selectCustomerId(String customerName,String user);

    @Select("select * from 销售拜访 LEFT  join  销售客户资料 on 销售拜访.客户资料Id=销售客户资料.Id where `销售拜访`.`客户经理`=#{uname} order by `销售拜访`.Id desc limit 10")
    @Results({
            @Result(property = "customerName", column = "客户名称"),
            @Result(property = "date", column = "时间"),
            @Result(property = "province", column = "省份"),
            @Result(property = "city", column = "城市"),
            @Result(property = "record", column = "拜访记录"),
            @Result(property = "address", column = "地址"),
            @Result(property = "contacts_1", column = "联系人姓名"),
            @Result(property = "contacts_2", column = "联系人2姓名"),
            @Result(property = "contacts_3", column = "联系人3姓名"),
            @Result(property = "tel_1", column = "联系人手机"),
            @Result(property = "tel_2", column = "联系人2手机"),
            @Result(property = "tel_3", column = "联系人3手机"),
            @Result(property = "productBrand", column = "供应商或干燥剂类型"),
            @Result(property = "productType", column = "干燥剂类型"),
            @Result(property = "consumption", column = "玻璃企业规模"),
            @Result(property = "customerType", column = "客户类别"),
            @Result(property = "price", column = "现用单价"),
            @Result(property = "imgStr", column = "附件")

    })
    List<Visit> getVisitsByUser(String uname);

    @Update("update 销售拜访 set 供应商或干燥剂类型=#{productBrand},现用单价=#{price},拜访记录=#{record} where id=#{id}")
    Integer updateVisit(Visit visit);

    @Select("select 客户资料Id from 销售拜访 where id=#{id}")
    String selectCustomerIdByVisitId(String id);
}
