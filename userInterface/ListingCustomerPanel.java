package userInterface;

import javax.swing.*;
import java.awt.*;

public class ListingCustomerPanel extends JPanel{

    private JTable jTable;

    public ListingCustomerPanel(){
        this.setLayout(new BorderLayout());

        this.jTable = new JTable(new ListingCustomerTableModel());
        this.jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.jTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.add(new JScrollPane(this.jTable), BorderLayout.CENTER);
    }

}
