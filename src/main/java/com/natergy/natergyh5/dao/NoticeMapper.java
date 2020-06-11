package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeMapper {
    @Select("select * from 销售通知 order by 发布时间 desc limit 10")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "publisher",column = "发布人"),
            @Result(property = "publishTime",column = "发布时间"),
            @Result(property = "noticeContent",column = "通知内容"),
            @Result(property = "toStaff",column = "通知人员"),
            @Result(property = "readStaff",column = "已阅人员"),
            @Result(property = "sendError",column = "未发送成功")
    })
    List<Notice> queryAllNotices();

    @Select("select 用户 from 用户 where 职位='国内业务经理' and 部门!='离职人员'")
    List<String> querySalesmanList();

    @Insert("insert into 销售通知 set 状态=#{status},发布人=#{publisher},发布时间=#{publishTime},通知内容=#{noticeContent},通知人员=#{toStaff}")
    @Options(useGeneratedKeys=true,keyColumn="Id",keyProperty="id")
    void saveNotice(Notice notice);

    @Select("select 未发送成功 from 销售通知 where Id=#{id}")
    String getError(String id);

    @Update("update 销售通知 set 未发送成功=#{error} where Id=#{id}")
    void updateError(String error, String id);

    @Select("select 通知内容 from 销售通知 where Id = #{id}")
    String getNoticeContentById(String id);


    @Select("select 已阅人员 from 销售通知 where Id=#{id}")
    String queryRead(String id);

    @Update("update 销售通知 set 已阅人员=#{strTmp} where Id=#{id}")
    void saveRead(String strTmp,String id);

    @Select("select 未发送成功 from 销售通知 where Id=#{id}")
    String getSendError(String id);

    @Select("select 通知人员 from 销售通知 where Id=#{id}")
    String getToStaff(String id);

    @Select("select * from 销售通知 order by 发布时间 desc limit #{limit},5 ")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "publisher",column = "发布人"),
            @Result(property = "publishTime",column = "发布时间"),
            @Result(property = "noticeContent",column = "通知内容"),
            @Result(property = "toStaff",column = "通知人员"),
            @Result(property = "readStaff",column = "已阅人员"),
            @Result(property = "sendError",column = "未发送成功")
    })
    List<Notice> reloadNotices(Integer limit);
}
