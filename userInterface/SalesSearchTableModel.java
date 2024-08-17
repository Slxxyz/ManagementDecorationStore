package userInterface;



import model.SalesSearchResult;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SalesSearchTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Date", "Chiffre d'affaire"};
    private List<SalesSearchResult> salesResults;

    public SalesSearchTableModel(List<SalesSearchResult> salesResults) {
        this.salesResults = salesResults;
    }

    @Override
    public int getRowCount() {
        return salesResults.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SalesSearchResult result = salesResults.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return result.getDate();
            case 1:
                return result.getSalesAmount();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
