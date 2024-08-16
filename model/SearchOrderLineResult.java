package model;


public class SearchOrderLineResult {

    private int orderLineCode;

    private int quantity;
    private Float productPrice;
    private String productLabel;
    private String productCategory;

    private String firstName;

    private String lastname;


    // Constructeur
    public SearchOrderLineResult(int orderLineCode, int quantity, String productLabel,Float productPrice, String productCategory, String lastName, String firstName) {
        this.orderLineCode = orderLineCode;
        this.quantity = quantity;
        this.productLabel = productLabel;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.firstName = firstName;
        this.lastname = lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }
}











