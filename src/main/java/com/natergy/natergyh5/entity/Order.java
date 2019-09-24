package com.natergy.natergyh5.entity;

import java.util.List;

/**
 * 订单bean
 * @author 四爷
 *
 */
public class Order {
	private String id;
	private String orderTime;
	private String orderNumber;
	private String customerName;
	private String consignee;
	private String receivingAddress;
	private String collector;
	private String invoiceAddress;
	private String attention;
	private String invoiceAttention;
	private String producer;
	private String state;
	private List<OrderDetail> orderDetails;
	@Override
	public String toString() {
		return "Order{" +
				"id='" + id + '\'' +
				", orderTime='" + orderTime + '\'' +
				", orderNumber='" + orderNumber + '\'' +
				", customerName='" + customerName + '\'' +
				", consignee='" + consignee + '\'' +
				", receivingAddress='" + receivingAddress + '\'' +
				", collector='" + collector + '\'' +
				", invoiceAddress='" + invoiceAddress + '\'' +
				", attention='" + attention + '\'' +
				", invoiceAttention='" + invoiceAttention + '\'' +
				", producer='" + producer + '\'' +
				", state='" + state + '\'' +
				", orderDetails=" + orderDetails +
				'}';
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getReceivingAddress() {
		return receivingAddress;
	}
	public void setReceivingAddress(String receivingAddress) {
		this.receivingAddress = receivingAddress;
	}
	public String getCollector() {
		return collector;
	}
	public void setCollector(String collector) {
		this.collector = collector;
	}
	public String getInvoiceAddress() {
		return invoiceAddress;
	}
	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getInvoiceAttention() {
		return invoiceAttention;
	}
	public void setInvoiceAttention(String invoiceAttention) {
		this.invoiceAttention = invoiceAttention;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Order(String orderTime, String orderNumber, String customerName, String consignee, String receivingAddress,
			String collector, String invoiceAddress, String attention, String invoiceAttention, String producer,
			List<OrderDetail> orderDetails) {
		super();
		this.orderTime = orderTime;
		this.orderNumber = orderNumber;
		this.customerName = customerName;
		this.consignee = consignee;
		this.receivingAddress = receivingAddress;
		this.collector = collector;
		this.invoiceAddress = invoiceAddress;
		this.attention = attention;
		this.invoiceAttention = invoiceAttention;
		this.producer = producer;
		this.orderDetails = orderDetails;
	}
	public Order() {
		super();
	}


}
