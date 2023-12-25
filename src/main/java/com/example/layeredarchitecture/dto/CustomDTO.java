package com.example.layeredarchitecture.dto;

import java.math.BigDecimal;

public class CustomDTO {
    private String id;
    private String name;
    private String oid;
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;

    public CustomDTO(String id, String name, String oid, String itemCode, int qty, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public CustomDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
