package controller;

import business.ProductManager;
import exception.ProductException;
import model.Product;
import java.util.ArrayList;

public class ProductController {
    private ProductManager manager;
    public ProductController() {
        setManager(new ProductManager());
    }
    public void setManager(ProductManager manager) {
        this.manager = manager;
    }
    //create
    public void createProduct(Product product)throws ProductException {
        this.manager.createProduct(product);
    }
    //read
    public Product readProduct(int productId) throws ProductException {
        return this.manager.readProduct(productId);
    }
    //update
    public void updateProduct(Product product) throws ProductException {
        this.manager.updateProduct(product);
    }
    //delete
    public void deleteProduct(int productId) throws ProductException {
        this.manager.deleteProduct(productId);
    }
    //readAll
    public ArrayList<Product> readAllProducts() throws ProductException {
        return this.manager.readAllProducts();
    }
}
