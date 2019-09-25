package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.FollowUp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FollowUpMapper {

    @Select("select * from 销售地产业务跟进 where 业务经理 = #{uname} order by Id desc limit 10")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "date", column = "创建日期"),
            @Result(property = "user", column = "业务经理"),
            @Result(property = "province", column = "省"),
            @Result(property = "city", column = "市"),
            @Result(property = "nowAddress", column = "所在地"),
            @Result(property = "customerName", column = "公司"),
            @Result(property = "address", column = "地址"),
            @Result(property = "contacts_1", column = "联系人1"),
            @Result(property = "contacts_2", column = "联系人2"),
            @Result(property = "tel_1", column = "移动电话1"),
            @Result(property = "tel_2", column = "移动电话2"),
            @Result(property = "post_1", column = "职位1"),
            @Result(property = "post_2", column = "职位2"),
            @Result(property = "tel", column = "固话"),
            @Result(property = "web", column = "网页"),
            @Result(property = "email", column = "电子邮件"),
            @Result(property = "chart_1", column = "聊天软件账号1"),
            @Result(property = "chart_2", column = "聊天软件账号2"),
            @Result(property = "industry", column = "行业"),
            @Result(property = "record", column = "跟进记录"),
            @Result(property = "relation", column = "影响关联")

    })
    List<FollowUp> getFollowUpByUser(String uname);


    @Insert("insert into 销售地产业务跟进(创建日期,业务经理,省,市,所在地,公司,行业,地址,固话,网页,电子邮件,联系人1,移动电话1,聊天软件账号1,部门1,职位1,联系人2,移动电话2,聊天软件账号2,部门2,职位2,图片附件,影响关联,跟进记录) values(#{date},#{user},#{province},#{city},#{nowAddress},#{customerName},#{industry},#{address},#{tel},#{web},#{email},#{contacts_1},#{tel_1},#{chart_1},#{department_1},#{post_1},#{contacts_2},#{tel_2},#{chart_2},#{department_2},#{post_2},'TODO',#{relation},#{record})")
    Integer saveFollowUp(FollowUp followUp);
}
