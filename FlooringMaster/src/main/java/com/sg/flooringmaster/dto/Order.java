/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dto;

import java.math.BigDecimal;

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
}
