package model;

import java.time.LocalDateTime;


public class OrderCustomer {
    private int code;
    private LocalDateTime dateAndTime;
    private String methodOfPayement;
    private int customer;

    public OrderCustomer(int code, LocalDateTime dateAndTime, String methodOfPayement, int customer) {
        this.code = code;
        this.dateAndTime = dateAndTime;
        this.methodOfPayement = methodOfPayement;
        this.customer = customer;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getMethodOfPayement() {
        return methodOfPayement;
    }

    public int getCustomer() {
        return customer;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setMethodOfPayement(String methodOfPayement) {
        this.methodOfPayement = methodOfPayement;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }
}
