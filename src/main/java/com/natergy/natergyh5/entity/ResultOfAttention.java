package com.natergy.natergyh5.entity;/**
 * 
 * @Author 杨枕戈
 * @Date 2020-04-21 8:38
 * @Version 1.0
 * 
 */
public class ResultOfAttention {
    private String attention;
    private String invoiceAttention;


    @Override
    public String toString() {
        return "ResultOfAttention{" +
                "attention='" + attention + '\'' +
                ", invoiceAttention='" + invoiceAttention + '\'' +
                '}';
    }

    public String getInvoiceAttention() {
        return invoiceAttention;
    }

    public void setInvoiceAttention(String invoiceAttention) {
        this.invoiceAttention = invoiceAttention;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }
}
