package interfaceAccess;

import exception.*;
import model.Product;

import java.util.ArrayList;

public class ProductDataAccess {

    void createProduct(Product product) throws ProductException 

    Product readProduct(int codeProduct) throws ProductException 

    void updateProduct(Product product) throws ProductException 
  
    void deleteProduct(int codeProduct) throws ProductException 

    ArrayList<Product> readAllProducts() throws ProductException 
     

}






