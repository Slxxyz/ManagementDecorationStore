package userInterface;

import model.SearchOrderCustomerResults;
import model.SearchOrderLineResult;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchOrderProductTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Order Code", "Order Date", "Payment Method", "Customer Last Name", "Customer First Name", "Order Line Code", "Quantity", "Product Label", "Product Price", "Product Category"};
    private final List<Object[]> tableData;

    public SearchOrderProductTableModel(Map<Integer, SearchOrderCustomerResults> orders) {
        this.tableData = new ArrayList<>();
        for (SearchOrderCustomerResults orderResult : orders.values()) {
            for (SearchOrderLineResult lineResult : orderResult.getOrderLines()) {
                tableData.add(new Object[]{
                        orderResult.getCode(),
                        orderResult.getDateAndTime(),
                        orderResult.getMethodOfPayment(),
                        lineResult.getLastname(),
                        lineResult.getFirstName(),
                        lineResult.getOrderLineCode(),
                        lineResult.getQuantity(),
                        lineResult.getProductLabel(),
                        lineResult.getProductPrice(),
                        lineResult.getProductCategory()
                });
            }
        }
        fireTableDataChanged();
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
