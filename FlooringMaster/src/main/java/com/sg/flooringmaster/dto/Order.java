/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Order {

    int orderNumber;
    String customerName;
    TaxRate stateTaxRate;
    Product product;
    BigDecimal area;
    BigDecimal materialCost;
    BigDecimal laborCost;
    BigDecimal tax;
    BigDecimal totalCost;

    public Order(int orderNumber, String customerName, TaxRate stateTaxRate, Product product, BigDecimal area, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal totalCost) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.stateTaxRate = stateTaxRate;
        this.product = product;
        this.area = area;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.totalCost = totalCost;
    }

    //Order(orderNumber,customerName,area,product,taxrate);
    public Order(int orderNumber, String customerName, TaxRate stateTaxRate, Product product, BigDecimal area) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.stateTaxRate = stateTaxRate;
        this.product = product;
        this.area = area;
        this.materialCost = area.multiply(this.product.getCostPerSquareFoot()).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.laborCost = area.multiply(this.product.getLaborCostPerSquareFoot()).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.tax = calculateTax();
        this.totalCost = (this.tax.add(this.laborCost).add(this.materialCost)).setScale(2, BigDecimal.ROUND_HALF_UP);

    }

    public Order() {
        this.orderNumber = 0;
        this.customerName = null;
        this.stateTaxRate = null;
        this.product = null;
        this.area = null;
        this.materialCost = null;
        this.laborCost = null;
        this.tax = null;
        this.totalCost = null;
    }

    private BigDecimal calculateTax() {
        // tax = ( stateTaxRate/100 ) * (laborCost + MaterialCost)
        return ((stateTaxRate.getTaxRate().divide(new BigDecimal("100.00"))).multiply(this.laborCost.add(this.materialCost))).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TaxRate getStateTaxRate() {
        return stateTaxRate;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setStateTaxRate(TaxRate stateTaxRate) {
        this.stateTaxRate = stateTaxRate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return orderNumber + " | " + customerName + " | " + stateTaxRate.getState() + " | " + stateTaxRate.getTaxRate().toString() + " | "
                + product.getProductType() + " | " + area.toString() + " | " + product.getCostPerSquareFoot().toString() + " | "
                + product.getLaborCostPerSquareFoot().toString() + " | " + materialCost.toString() + " | "
                + laborCost.toString() + " | " + tax.toString() + " | " + totalCost.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.orderNumber;
        hash = 47 * hash + Objects.hashCode(this.customerName);
        hash = 47 * hash + Objects.hashCode(this.stateTaxRate);
        hash = 47 * hash + Objects.hashCode(this.product);
        hash = 47 * hash + Objects.hashCode(this.area);
        hash = 47 * hash + Objects.hashCode(this.materialCost);
        hash = 47 * hash + Objects.hashCode(this.laborCost);
        hash = 47 * hash + Objects.hashCode(this.tax);
        hash = 47 * hash + Objects.hashCode(this.totalCost);
        return hash;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.stateTaxRate, other.stateTaxRate)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        return true;
    }

}
