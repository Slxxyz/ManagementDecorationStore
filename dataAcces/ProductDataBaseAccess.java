package dataAccess;

import exception.*;
import model.Product;
import java.sql.*;
import java.util.ArrayList;


public class ProductDataBaseAccess {
    //create
    public void createProduct(Product product) throws ProductException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query =
                    """
                    INSERT INTO product
                    VALUES (?,?,?,?,?,?,?,?);
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,product.getCode());
            statement.setString(2,product.getLabelProduct());
            statement.setDouble(3,product.getUnitPriceExcludingTax());
            statement.setInt(4,product.getVATRate());
            statement.setInt(5,product.getLoyaltyPointsValue());
            statement.setInt(6,product.getQuantityInStock());
            statement.setBoolean(7,product.getIsFragile());
            statement.setString(8,product.getCategory());
            statement.executeUpdate();
            }
        catch (SQLException exception) {
            throw new ProductException(exception.getMessage(), new OneException(), new CreateException());
        }
    }
    //read
    public Product readProduct(int codeProduct) throws ProductException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM product WHERE code = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codeProduct);
            ResultSet data = statement.executeQuery();
            data.next();
            int code = data.getInt("code");
            String labelProduct = data.getString("labelProduct");
            double unitPriceExcludingTax = data.getDouble("unitPriceExcludingTax");
            int vATRate = data.getInt("vATRate");
            int loyaltyPointsValue = data.getInt("loyaltyPointsValue");
            int quantityInStock = data.getInt("quantityInStock");
            boolean isFragile = data.getBoolean("isFragile");
            String category = data.getString("category");
            return new Product(code, labelProduct, unitPriceExcludingTax, vATRate, loyaltyPointsValue, quantityInStock, isFragile, category);
        } catch (SQLException exception) {
            throw new ProductException(exception.getMessage(), new OneException(), new ReadException());
        }
    }

    //update
    public void updateProduct(Product product) throws ProductException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query =
                    """
                    UPDATE product
                    SET labelProduct = ?, unitPriceExcludingTax = ?, vATRate = ?, loyaltyPointsValue = ?, quantityInStock = ?, isFragile = ?, category = ?
                    WHERE code = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,product.getLabelProduct());
            statement.setDouble(2,product.getUnitPriceExcludingTax());
            statement.setInt(3,product.getVATRate());
            statement.setInt(4,product.getLoyaltyPointsValue());
            statement.setInt(5,product.getQuantityInStock());
            statement.setBoolean(6,product.getIsFragile());
            statement.setString(7,product.getCategory());
            statement.setInt(8,product.getCode());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new ProductException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }

    //delete
    public void deleteProduct(int codeProduct) throws ProductException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String query = "DELETE FROM product WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,codeProduct);
            statement.executeUpdate();

        } catch(SQLException exception) {
            throw new ProductException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }

    //readAll
    public ArrayList<Product> readAllProducts() throws ProductException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM product;";
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            ArrayList<Product> products = new ArrayList<Product>();
            while (data.next()) {
                int code = data.getInt("code");
                String labelProduct = data.getString("labelProduct");
                double unitPriceExcludingTax = data.getDouble("unitPriceExcludingTax");
                int vATRate = data.getInt("vATRate");
                int loyaltyPointsValue = data.getInt("loyaltyPointsValue");
                int quantityInStock = data.getInt("quantityInStock");
                boolean isFragile = data.getBoolean("isFragile");
                String category = data.getString("category");
                Product product = new Product(code, labelProduct, unitPriceExcludingTax, vATRate, loyaltyPointsValue, quantityInStock, isFragile, category);
                products.add(product);
            }
            return products;
        } catch (SQLException exception) {
            throw new ProductException(exception.getMessage(), new AllException(), new ReadException());
        }
    }
}
