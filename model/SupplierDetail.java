package model;

import java.time.LocalDateTime;



public class SupplierDetail {
    private int product;
    private String supplier;
    private LocalDateTime dateAndTime;
    private int quantity;
    private double unitPrice;
    private int minimumQuantity;


    public SupplierDetail(int product, String supplier, LocalDateTime dateAndTime, int quantity, double unitPrice, int minimumQuantity) {
        this.product = product;
        this.supplier = supplier;
        this.dateAndTime = dateAndTime;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.minimumQuantity = minimumQuantity;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public int getProduct() {
        return product;
    }

    public String getSupplier() {
        return supplier;
    }
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setProduct(int product) {
        this.product = product;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

}
