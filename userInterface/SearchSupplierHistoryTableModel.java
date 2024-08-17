package userInterface;

import controller.CategoryController;
import controller.ProductController;
import model.Category;
import model.Product;
import model.SearchSupplierDetailResult;


import javax.swing.table.AbstractTableModel;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchSupplierHistoryTableModel extends AbstractTableModel {
    private String[] columnNames;
    private ArrayList<SearchSupplierDetailResult> searchSupplierDetailResults;
    private CategoryController categoryController;
    private ProductController productController;

    public SearchSupplierHistoryTableModel(ArrayList<SearchSupplierDetailResult> searchSupplierDetailResults) {
        this.categoryController = new CategoryController();
        this.productController = new ProductController();
        this.columnNames = new String[]{
                "Date de livraison",
                "Quantité",
                "Label du produit",
                "Catégorie du produit"
        };
        setSearchSupplierDetailResults(searchSupplierDetailResults);
    }

    @Override
    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    @Override
    public int getRowCount() {
        return searchSupplierDetailResults.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SearchSupplierDetailResult supplierDetailResult = searchSupplierDetailResults.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return supplierDetailResult.getDateAndTime();
            case 1:
                return supplierDetailResult.getQuantity();
            case 2:
                try {
                    Product product = productController.readProduct(supplierDetailResult.getProductCode());
                    return product.getLabelProduct();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                return "Erreur de chargement";
            case 3:
                try {
                    Category category = categoryController.readCategory(supplierDetailResult.getCategoryName());
                    return category.getCategoryName();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                return "Erreur de chargement";
            default:
                return null;
        }
    }


    public Class getColumnClass(int column) {
        return switch (column) {
            case 0 -> LocalDate.class;
            case 1 -> Integer.class;
            case 2 -> String.class;
            case 3 -> String.class;
            default -> Object.class;
        };
    }

    public void setSearchSupplierDetailResults(ArrayList<SearchSupplierDetailResult> searchSupplierDetailResults) {
        this.searchSupplierDetailResults = searchSupplierDetailResults;
    }
}
