package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SupplierDetail {
    private int product;
    private String supplier;
    private Timestamp dateAndTime;
    private int quantity;
    private double unitPrice;
    private Integer minimumQuantity;


    public SupplierDetail(int product, String supplier, Timestamp dateAndTime, int quantity, double unitPrice) {
        this.product = product;
        this.supplier = supplier;
        this.dateAndTime = dateAndTime;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public int getProduct() {
        return product;
    }

    public String getSupplier() {
        return supplier;
    }
    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setProduct(int product) {
        this.product = product;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        if(minimumQuantity==null || minimumQuantity<=0){
            this.minimumQuantity = null;
        }else{
            this.minimumQuantity = minimumQuantity;
        }
    }

}
