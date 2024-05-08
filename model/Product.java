package model;



public class Product {
    private int code;
    private String labelProduct;
    private double unitPriceExcludingTax;

    private int vATRate;
    private int loyaltyPointsValue;
    private int quantityInStock;
    private boolean isFragile;
    private String category;


public Product(int code, String labelProduct, double unitPriceExcludingTax, int vATRate, int loyaltyPointsValue, int quantityInStock, boolean isFragile, String category) {
        this.code = code;
        this.labelProduct = labelProduct;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vATRate = vATRate;
        this.loyaltyPointsValue = loyaltyPointsValue;
        this.quantityInStock = quantityInStock;
        this.isFragile = isFragile;
        this.category = category;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public String getLabelProduct() {
        return labelProduct;
    }

    public double getUnitPriceExcludingTax() {
        return unitPriceExcludingTax;
    }

    public int getVATRate() {
        return vATRate;
    }

    public int getLoyaltyPointsValue() {
        return loyaltyPointsValue;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public boolean getIsFragile() {
        return isFragile;
    }

    public String getCategory() {
        return category;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setLabelProduct(String label) {
        this.labelProduct = label;
    }

    public void setUnitPriceExcludingTax(double unitPriceExcludingTax) {
        this.unitPriceExcludingTax = unitPriceExcludingTax;
    }

    public void setVATRate(int vATRate) {
        this.vATRate = vATRate;
    }

    public void setLoyaltyPointsValue(int loyaltyPointsValue) {
        this.loyaltyPointsValue = loyaltyPointsValue;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setIsFragile(boolean isFragile) {
        this.isFragile = isFragile;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
