package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Entry;
import com.natergy.natergyh5.entity.Offer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PriceListMapper {

    @Insert("insert into 报价单 set 状态=#{status},日期=#{date},业务经理=#{user},客户名称=#{companyName}")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="Id")
    void savePriceList(Offer offer);

    @Insert("insert into 报价单明细 set 状态=#{status},规格型号=#{size},`单价(元)`=#{price},报价单Id=#{offerId}")
    void savePriceListDetail(Entry entry);
}
