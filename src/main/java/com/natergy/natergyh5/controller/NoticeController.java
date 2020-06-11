package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Notice;
import com.natergy.natergyh5.entity.Working;
import com.natergy.natergyh5.service.NoticeService;
import com.natergy.natergyh5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author 杨枕戈
 * @Date 2020-03-07 8:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Value("${SalesExecutive}")
    private String salesExecutive;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;


    @RequestMapping("/init")
    public ModelAndView init(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String uname = (String) session.getAttribute("user");
        if (!salesExecutive.contains(uname)) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('您没有下发通知权限，需要请联系技术三部！');window.location.href='" + request.getContextPath() + "/jsp/main.jsp';</script>");
            return null;
        }
        List<Notice> noticeList = noticeService.queryAllNotices();
        List<String> salesmanList = noticeService.querySalesmanList();
        ModelAndView mv = new ModelAndView("forward:/jsp/notice/notice.jsp");
        mv.addObject("noticeList", JSON.toJSONString(noticeList));
        return mv;
    }

    @RequestMapping("/getSalesmanList")
    public String getSalesmanList() {
        List<String> salesmanList = noticeService.querySalesmanList();
        return JSON.toJSONString(salesmanList);
    }

    @RequestMapping("/save")
    public String saveNotice(@RequestBody HashMap<String, String> jsonMap,HttpSession session) throws IOException {
        String uname = (String) session.getAttribute("user");
        String noticeContent = jsonMap.get("noticeContent");
        String staffList = jsonMap.get("str");
        Notice notice=new Notice();
        notice.setPublisher(uname);
        notice.setNoticeContent(noticeContent);
        notice.setPublishTime(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
        notice.setToStaff(staffList);
        notice.setStatus("新");
        String reten = noticeService.saveNotice(notice);
        if("ok".equals(reten)){
            String toStaff =noticeService.getToStaff(notice.getId());
            String sendError=noticeService.getSendError(notice.getId());
            if(!"".equals(sendError)){
                return JSON.toJSONString("已成功通知："+toStaff+"\n未成功通知："+sendError);
            }else{
                return JSON.toJSONString("全部发送成功");
            }

        }else {
            return JSON.toJSONString("error");
        }

    }

    @RequestMapping("/viewInit/{id}")
    public ModelAndView viewInit(@PathVariable String id,HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        String username = userService.getUsernameByOpenId(code);
        request.getSession().setAttribute("user",username);
        ModelAndView mv =new ModelAndView("forward:/jsp/notice/noticeView.jsp");
        String  noticeContent = noticeService.getNoticeContentById(id);
        mv.addObject("noticeContent",JSON.toJSONString(noticeContent));
        mv.addObject("id",JSON.toJSONString(id));
        return mv;
    }

    @RequestMapping("/saveRead")
    public void saveRead(HttpSession session,@RequestParam("id")String id){
        String uname = (String) session.getAttribute("user");
        noticeService.saveRead(uname,id);
    }

    @RequestMapping("/refresh")
    public List<Notice> refresh(){
        List<Notice>noticeList = noticeService.queryAllNotices();
        return noticeList;
    }

    @RequestMapping("/reload")
    public List<Notice> workingReload(Integer limit, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        List<Notice> resultList=noticeService.reloadNotices(limit);
        return resultList;
    }




}
