package model;


public class Category {
    private String categoryName;
    private String description;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }
    //////////////////////////////////////////////////////////////////SETTERS
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        if(description.isBlank() || description.isEmpty()){
            this.description = null;
        }else{
            this.description = description;
        }
    }

    @Override
    public String toString() {
        return categoryName + "(" +description+ ")";
    }

}
