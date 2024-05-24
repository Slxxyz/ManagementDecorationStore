package userInterface;

import controller.*;
import exception.*;
import model.Customer;
import model.OrderCustomer;
import model.OrderLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.OK_OPTION;

public class DeleteCustomerPanel extends JPanel implements ActionListener {

    private JTable jTable;
    private CustomerController customerController;
    private OrderCustomerController orderCustomerController;
    private OrderLineController orderLineController;
    private JButton deleteButton;
    private ListSelectionModel listSelectionModelCustomer;

    public DeleteCustomerPanel(){
        this.setLayout (new BorderLayout());

        this.customerController = new CustomerController();
        this.orderCustomerController = new OrderCustomerController();
        this.orderLineController = new OrderLineController();

        this.deleteButton = new JButton("Supprimer");
        this.deleteButton.addActionListener(this);

        this.jTable = new JTable(new ListingCustomerTableModel());
        this.jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.jTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.listSelectionModelCustomer = this.jTable.getSelectionModel();

        this.add(new JScrollPane(this.jTable), BorderLayout.CENTER);
        this.add(this.deleteButton, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent event){
        String source = event.getActionCommand();
        if(source.equals("Supprimer")){
            int[] selectedRows = this.listSelectionModelCustomer.getSelectedIndices();
            if(selectedRows.length>0){
                try{
                    int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer les clients selectionnés ?", "Cette action est irréversible !", JOptionPane.YES_NO_OPTION);
                    if(confirmation == OK_OPTION){
                        ArrayList<Customer> customers = customerController.readAllCustomers();
                        for (int selectedRow : selectedRows) {
                            int numberCustomer = customers.get(selectedRow).getNumber();
                            ArrayList<OrderCustomer> orderCustomers = orderCustomerController.readAllOrderCustomers();
                            for (OrderCustomer orderCustomer : orderCustomers) {
                                if (orderCustomer.getCustomer() == numberCustomer) {
                                    this.orderLineController.deleteAllOrderLinesFor(orderCustomer.getCode());
                                    this.orderCustomerController.deleteOrderCustomer(numberCustomer);
                                }
                            }
                            this.customerController.deleteCustomer(numberCustomer);
                        }
                        this.listSelectionModelCustomer.clearSelection();
                        this.repaint();
                        this.revalidate();
                    }
                }catch (ExceptionType exception){
                    JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "Vous devez selectionner un client", "Selectionner les clients", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
