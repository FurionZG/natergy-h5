package com.natergy.natergyh5.entity;
/**
 * 订单明细bean
 * @author 四爷
 *
 */
public class OrderDetail {

	private String size;
	private String innerWrapper;
	private String outwrapper;
	private String count;
	private String tax;
	private String orderNumber;
	private String price;
	private String rebate;
	private String totalPrice;
	private String totalWeight;
	

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	





	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getInnerWrapper() {
		return innerWrapper;
	}

	public void setInnerWrapper(String innerWrapper) {
		this.innerWrapper = innerWrapper;
	}

	public String getOutwrapper() {
		return outwrapper;
	}

	public void setOutwrapper(String outwrapper) {
		this.outwrapper = outwrapper;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDetail{" +
				"size='" + size + '\'' +
				", innerWrapper='" + innerWrapper + '\'' +
				", outwrapper='" + outwrapper + '\'' +
				", count='" + count + '\'' +
				", tax='" + tax + '\'' +
				", orderNumber='" + orderNumber + '\'' +
				", price='" + price + '\'' +
				", rebate='" + rebate + '\'' +
				", totalPrice='" + totalPrice + '\'' +
				", totalWeight='" + totalWeight + '\'' +
				'}';
	}

	public OrderDetail(String size, String innerWrapper, String outwrapper, String count, String tax, String orderNumber, String price, String rebate, String totalPrice, String totalWeight) {
		this.size = size;
		this.innerWrapper = innerWrapper;
		this.outwrapper = outwrapper;
		this.count = count;
		this.tax = tax;
		this.orderNumber = orderNumber;
		this.price = price;
		this.rebate = rebate;
		this.totalPrice = totalPrice;
		this.totalWeight = totalWeight;
	}

	public OrderDetail() {
		super();
	}

	

	

}
