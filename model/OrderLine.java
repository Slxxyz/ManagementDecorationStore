package model;

public class OrderLine {
    private int code;
    private int quantity;
    private int orderCustomer;
    private int product;

    public OrderLine(int code, int quantity, int orderCustomer, int product) {
        this.code = code;
        this.quantity = quantity;
        this.orderCustomer = orderCustomer;
        this.product = product;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderCustomer() {
        return orderCustomer;
    }

    public int getProduct() {
        return product;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrderCustomer(int orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
