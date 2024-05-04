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
}
