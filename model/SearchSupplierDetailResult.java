package model;
import java.time.LocalDate;
public class SearchSupplierDetailResult {
    private LocalDate dateAndTime;
    private int quantity;
    private String labelProduct;
    private String categoryName;

    private int productCode;

    // Constructeur
    public SearchSupplierDetailResult(LocalDate dateAndTime, int quantity, String labelProduct, String categoryName,int productCode) {
        this.dateAndTime = dateAndTime;
        this.quantity = quantity;
        this.labelProduct = labelProduct;
        this.categoryName = categoryName;
        this.productCode = productCode;
    }

    // Getters
    public LocalDate getDateAndTime() {
        return dateAndTime;
    }

    public int getQuantity() {
        return quantity;
    }


    public String getLabelProduct() {
        return labelProduct;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getProductCode(){
        return productCode;
    }
}
