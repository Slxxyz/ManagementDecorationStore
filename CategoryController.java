package controller;

import business.CategoryManager;
import exception.CategoryException;
import model.Category;
import java.util.ArrayList;

public class CategoryController {
    private CategoryManager manager;
    public CategoryController() {
        setManager(new CategoryManager());
    }
    public void setManager(CategoryManager manager) {
        this.manager = manager;
    }
    //read
    public Category readCategory(String categoryNameId)throws CategoryException {
        return this.manager.readCategory(categoryNameId);
    }
    //readAll
    public ArrayList<Category> readAllCategories() throws CategoryException {
        return this.manager.readAllCategories();
    }
}
