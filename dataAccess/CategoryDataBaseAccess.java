package dataAccess;

import exception.*;
import model.Category;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.CategoryDataAccess;


public class CategoryDataBaseAccess implements CategoryDataAccess{
    //read
    @Override
    public Category readCategory(String categoryNameId) throws CategoryException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM category WHERE categoryName = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, categoryNameId);
            ResultSet data = statement.executeQuery();
            data.next();
            String categoryName= data.getString("categoryName");
            Category category = new Category(categoryName);
            String description = data.getString("descriptionCategory");
            if (!data.wasNull()){
                category.setDescription(description);
            }
            return category;
        } catch (SQLException exception) {
            throw new CategoryException(exception.getMessage(), new OneException(), new ReadException());
        }
    }

    //readAll
    @Override
    public ArrayList<Category> readAllCategories() throws CategoryException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM category;";
            Statement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery(query);
            ArrayList<Category> categories = new ArrayList<Category>();
            while (data.next()) {
                String categoryName = data.getString("categoryName");
                Category category = new Category(categoryName);
                String description = data.getString("descriptionCategory");
                if (!data.wasNull()){
                    category.setDescription(description);
                }
                categories.add(category);
            }
            return categories;
        } catch (SQLException exception) {
            throw new CategoryException(exception.getMessage(), new AllException(), new ReadException());
        }
    }
}
