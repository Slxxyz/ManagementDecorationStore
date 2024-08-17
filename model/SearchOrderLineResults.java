package model;


public class SearchOrderLineResults {

    private int orderLineCode;

    private int quantity;
    private Float productPrice;
    private String productLabel;
    private String productCategory;




    // Constructeur
    public SearchOrderLineResults(int orderLineCode, int quantity, String productLabel,Float productPrice, String productCategory) {
        this.orderLineCode = orderLineCode;
        this.quantity = quantity;
        this.productLabel = productLabel;
        this.productPrice = productPrice;
        this.productCategory = productCategory;

    }

    // Getters
    public int getOrderLineCode() {
        return orderLineCode;
    }


    public int getQuantity() {
        return quantity;
    }


    public String getProductLabel() {
        return productLabel;
    }

    public float getProductPrice(){
        return productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
