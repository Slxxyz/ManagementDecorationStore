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
    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public double getUnitPriceExcludingTax() {
        return unitPriceExcludingTax;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public int getVATRate() {
        return vATRate;
    }

    public boolean getIsFragile() {
        return isFragile;
    }

    public int getLoyaltyPointsValue() {
        return loyaltyPointsValue;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setUnitPriceExcludingTax(double unitPriceExcludingTax) {
        this.unitPriceExcludingTax = unitPriceExcludingTax;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setVATRate(int vATRate) {
        this.vATRate = vATRate;
    }

    public void setIsFragile(boolean isFragile) {
        this.isFragile = isFragile;
    }

    public void setLoyaltyPointsValue(int loyaltyPointsValue) {
        this.loyaltyPointsValue = loyaltyPointsValue;
    }



}
