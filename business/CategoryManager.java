package business;

import dataAccess.CategoryDataBaseAccess;
import interfaceAccess.CategoryDataAccess;
import exception.CategoryException;
import model.Category;
import java.util.ArrayList;

public class CategoryManager {
    private CategoryDataAccess categoryAccess;
    public CategoryManager(){
        setCategoryManager(new CategoryDataBaseAccess());
    }
    public void setCategoryManager(CategoryDataAccess categoryAccess){
        this.categoryAccess = categoryAccess;
    }
    //read
public Category readCategory(String categoryNameId) throws CategoryException{
        return this.categoryAccess.readCategory(categoryNameId);
    }
    //readAll
    public ArrayList<Category> readAllCategories() throws CategoryException{
        return this.categoryAccess.readAllCategories();
    }

}
