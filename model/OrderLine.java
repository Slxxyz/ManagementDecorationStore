package model;

public class OrderLine {
    private int code;
    private int quantity;

    public OrderLine(int code, int quantity) {
        this.code = code;
        this.quantity = quantity;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
