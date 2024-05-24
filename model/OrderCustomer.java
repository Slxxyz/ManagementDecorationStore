package model;

import java.sql.Timestamp;

public class OrderCustomer {
    private int codeOrder;
    private Timestamp dateAndTime;
    private String methodOfPayment;
    private int customer;

    public OrderCustomer(int codeOrder, Timestamp dateAndTime, String methodOfPayment, int customer) {
        this.codeOrder = codeOrder;
        this.dateAndTime = dateAndTime;
        this.methodOfPayment = methodOfPayment;
        this.customer = customer;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return codeOrder;
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

    public void setCode(int codeOrder) {
        this.codeOrder = codeOrder;
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
