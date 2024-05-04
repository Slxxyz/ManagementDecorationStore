package model;

public class Product {
    private int code;
    private String label;
    private double unitPriceExcludingTax;
    private int quantityInStock;
    private int vATRate;
    private boolean isFragile;
    private int loyaltyPointsValue;

    public Product(int code, String label, double unitPriceExcludingTax, int quantityInStock, int vATRate, boolean isFragile, int loyaltyPointsValue) {
        this.code = code;
        this.label = label;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.quantityInStock = quantityInStock;
        this.vATRate = vATRate;
        this.isFragile = isFragile;
        this.loyaltyPointsValue = loyaltyPointsValue;
    }

}
