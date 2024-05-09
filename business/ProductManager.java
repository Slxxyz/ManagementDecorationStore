package business;

import dataAccess.ProductDataBaseAccess;
import interfaceAccess.ProductDataAccess;
import exception.ProductException;
import model.Product;
import java.util.ArrayList;

public class ProductManager {
    private ProductDataAccess dataAccess;

    public ProductManager() {
        setProductManager(new ProductDataBaseAccess());
    }
    public void setProductManager(ProductDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
    //create
    public void createProduct(Product product) throws ProductException {
        dataAccess.createProduct(product);
    }
    //read
    public Product readProduct(int productId) throws ProductException {
        return dataAccess.readProduct(productId);
    }
    //update
    public void updateProduct(Product product) throws ProductException {
        dataAccess.updateProduct(product);
    }
    //delete
    public void deleteProduct(int productId) throws ProductException {
        dataAccess.deleteProduct(productId);
    }
    //readAll
    public ArrayList<Product> readAllProducts() throws ProductException {
        return dataAccess.readAllProducts();
    }

}
