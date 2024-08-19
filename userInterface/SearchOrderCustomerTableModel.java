package userInterface;

import model.SearchOrderCustomerResult;
import model.SearchOrderLineResults;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchOrderCustomerTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Code de la commande", "Date", "Méthode de payement", "Nom du produit", "Quantité", "Prix du produit", "Catégorie du produit"};
    private final List<Object[]> tableData;

    public SearchOrderCustomerTableModel(Map<Integer, SearchOrderCustomerResult> searchOrderCustomerResults) {
        tableData = new ArrayList<>();
        for (SearchOrderCustomerResult orderResult : searchOrderCustomerResults.values()) {
            for (SearchOrderLineResults lineResult : orderResult.getOrderLines()) {
                tableData.add(new Object[]{
                        orderResult.getCode(),
                        orderResult.getDateAndTime(),
                        orderResult.getMethodOfPayment(),
                        lineResult.getProductLabel(),
                        lineResult.getQuantity(),
                        lineResult.getProductPrice(),
                        lineResult.getProductCategory()
                });
            }
        }
    }

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
