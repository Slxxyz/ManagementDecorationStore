package userInterface;

import controller.CustomerController;
import exception.ExceptionType;
import exception.NumberCustomerException;
import model.Customer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListingCustomerTableModel extends AbstractTableModel{
    private String[] columnNames;
    private CustomerController customerController;

    public ListingCustomerTableModel(){

        this.columnNames = new String[]{
                "Numéro",
                "Nom",
                "Prenom",
                "Genre",
                "Ville",
                "Date de Naissance",
                "Nombre de points",
                "Telephone",
                "Email",
                "Est marié ?",
                };
        this.customerController = new CustomerController();
    }
    public int getColumnCount() {
        return columnNames.length;
    }
    public int getRowCount() {
        try{
            return customerController.getNumberCustomer();
        } catch (NumberCustomerException exception) {
            System.out.println(exception.getMessage());
            return 0;
        }
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int column){
        return switch (column) {
            case 0 -> int.class;
            case 1 -> String.class;
            case 2 -> String.class;
            case 3 -> String.class;
            case 4 -> String.class;
            case 5 -> LocalDate.class;
            case 6 -> int.class;
            case 7 -> String.class;
            case 8 -> String.class;
            case 9 -> boolean.class;
            default -> String.class;
        };
    }
    public Object getValueAt(int row, int col) {
        try {
            ArrayList<Customer> customers = customerController.readAllCustomers();
            Customer customer = customers.get(row);

            return switch (col) {
                case 0 -> customer.getNumber();
                case 1 -> customer.getLastName();
                case 2 -> customer.getFirstName();
                case 3 -> customer.getGender();
                case 4 -> customer.getCityName(customer.getCityId());
                case 5 -> customer.getBirthday();
                case 6 -> customer.getPointNb();
                case 7 -> customer.getTelNumber();
                case 8 -> customer.getMailAddress();
                case 9 -> customer.getIsMarried();
                default -> null;
            };
        }catch (ExceptionType exception) {
            JOptionPane.showMessageDialog(null,exception.getDescription(),exception.getMessage() , JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }


}
