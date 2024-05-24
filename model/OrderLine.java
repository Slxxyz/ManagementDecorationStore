package model;

public class OrderLine {
    private int codeOrderLine;
    private int quantity;
    private int orderCustomer;
    private int product;

    public OrderLine(int codeOrderLine, int quantity, int orderCustomer, int product) {
        this.codeOrderLine = codeOrderLine;
        this.quantity = quantity;
        this.orderCustomer = orderCustomer;
        this.product = product;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return codeOrderLine;
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

    public void setCode(int codeOrderLine) {
        this.codeOrderLine = codeOrderLine;
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
