package com.natergy.natergyh5.entity;/**
 * 
 * @Author 杨枕戈
 * @Date 2020-03-07 10:35
 * @Version 1.0
 * 
 */
public class Notice {
    private String Id;
    private String status;
    private String publisher;
    private String publishTime;
    private String noticeContent;
    private String toStaff;
    private String readStaff;
    private String sendError;

    @Override
    public String toString() {
        return "Notice{" +
                "Id='" + Id + '\'' +
                ", status='" + status + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", toStaff='" + toStaff + '\'' +
                ", readStaff='" + readStaff + '\'' +
                ", sendError='" + sendError + '\'' +
                '}';
    }

    public String getSendError() {
        return sendError;
    }

    public void setSendError(String sendError) {
        this.sendError = sendError;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getToStaff() {
        return toStaff;
    }

    public void setToStaff(String toStaff) {
        this.toStaff = toStaff;
    }

    public String getReadStaff() {
        return readStaff;
    }

    public void setReadStaff(String readStaff) {
        this.readStaff = readStaff;
    }
}
