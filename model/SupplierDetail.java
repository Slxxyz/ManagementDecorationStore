package model;

import java.time.LocalDateTime;

public class SupplierDetail {
    private LocalDateTime dateAndTime;
    private int quantity;
    private double unitPrice;
    private double minimumQuantity;

    public SupplierDetail(LocalDateTime dateAndTime, int quantity, double unitPrice, double minimumQuantity) {
        this.dateAndTime = dateAndTime;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.minimumQuantity = minimumQuantity;
    }
    //////////////////////////////////////////////////////////////////GETTERS

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getMinimumQuantity() {
        return minimumQuantity;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setMinimumQuantity(double minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }
}
