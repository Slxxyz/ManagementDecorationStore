package model;

import java.sql.Timestamp;

public class OrderCustomer {
    private int code;
    private Timestamp dateAndTime;
    private String methodOfPayment;
    private int customer;

    public OrderCustomer(int code, Timestamp dateAndTime, String methodOfPayment, int customer) {
        this.code = code;
        this.dateAndTime = dateAndTime;
        this.methodOfPayment = methodOfPayment;
        this.customer = customer;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }

    public int getCustomer() {
        return customer;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }


}
