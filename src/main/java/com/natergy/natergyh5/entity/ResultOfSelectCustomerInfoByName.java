package com.natergy.natergyh5.entity;
/**
 * 客户信息bean
 * @author 四爷
 *
 */
public class ResultOfSelectCustomerInfoByName {
	private String customerName;
	private String consignee;
	private String receivingAddress;
	private String collector;
	private String invoiceAddress;
	private String attention;
	private String invoiceAttention;
	private String producer;
	private String id;
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInvoiceAttention(String invoiceAttention) {
		this.invoiceAttention = invoiceAttention;
	}

	@Override
	public String toString() {
		return "ResultOfSelectCustomerInfoByName [customerName=" + customerName + ", consignee=" + consignee
				+ ", receivingAddress=" + receivingAddress + ", collector=" + collector + ", invoiceAddress="
				+ invoiceAddress + ", attention=" + attention + ", invoiceAttention=" + invoiceAttention + ", producer="
				+ producer + ", id=" + id + "]";
	}

	

}
