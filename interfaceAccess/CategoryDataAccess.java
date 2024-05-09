package interfaceAccess;

import exception.CategoryException;
import model.Category;
import java.util.ArrayList;

public interface CategoryDataAccess {
    //read
    public Category readCategory(String categoryNameId) throws CategoryException;
    //readAll
    public ArrayList<Category> readAllCategories() throws CategoryException;
}
