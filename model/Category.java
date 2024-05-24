package model;


public class Category {
    private String categoryName;
    private String descriptionCategory;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return descriptionCategory;
    }
    //////////////////////////////////////////////////////////////////SETTERS
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String descriptionCategory) {
        if(descriptionCategory.isBlank() || descriptionCategory.isEmpty()){
            this.descriptionCategory = null;
        }else{
            this.descriptionCategory = descriptionCategory;
        }
    }

    @Override
    public String toString() {
        return categoryName + "(" +descriptionCategory+ ")";
    }

}
