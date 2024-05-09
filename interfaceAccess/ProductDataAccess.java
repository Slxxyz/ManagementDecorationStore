package interfaceAccess;

import exception.ProductException;
import model.Product;
import java.util.ArrayList;
public interface ProductDataAccess {
    //create
    public void createProduct(Product product) throws ProductException;
    //read
    public Product readProduct(int productId) throws ProductException;
    //update
    public void updateProduct(Product product) throws ProductException;
    //delete
    public void deleteProduct(int productId) throws ProductException;
    //readAll
    public ArrayList<Product> readAllProducts() throws ProductException;
}
