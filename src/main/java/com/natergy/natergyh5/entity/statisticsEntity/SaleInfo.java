package com.natergy.natergyh5.entity.statisticsEntity;


import java.math.BigDecimal;

public class SaleInfo implements Comparable {

    private BigDecimal totalNetWeight = new BigDecimal(0d);
    private BigDecimal totalPrice = new BigDecimal(0d);


    public void add(BigDecimal netWeight, BigDecimal price) {
        this.totalNetWeight = this.totalNetWeight.add(netWeight);

        this.totalPrice = this.totalPrice.add(price);
    }



    public void subtract(BigDecimal netWeight, BigDecimal price) {
        this.totalNetWeight = this.totalNetWeight.subtract(netWeight);
        this.totalPrice = this.totalPrice.subtract(price);
    }

    public BigDecimal getTotalNetWeight() {
        return totalNetWeight;
    }

    public void setTotalNetWeight(BigDecimal totalNetWeight) {
        this.totalNetWeight = totalNetWeight;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "SaleInfo{" +
                "totalNetWeight=" + totalNetWeight +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        if (o instanceof SaleInfo) {
            SaleInfo other = (SaleInfo) o;
            int pricecompare = this.getTotalPrice().compareTo(other.getTotalPrice());
            int weightcompare = this.getTotalNetWeight().compareTo(other.getTotalNetWeight());

            return pricecompare&weightcompare;
        }

        return 0;
    }
}
