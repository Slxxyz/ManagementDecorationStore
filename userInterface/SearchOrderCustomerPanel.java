package userInterface;

import controller.CustomerController;
import controller.SearchController;

import model.Customer;
import model.SearchOrderCustomerResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class SearchOrderCustomerPanel extends JPanel implements ActionListener {
    private JComboBox<Customer> customerComboBox;
    private DefaultComboBoxModel<Customer> customerComboBoxModel;
    private JLabel customerErrorLabel;
    private JTable orderDetailTable;
    private JButton submitButton;
    private CustomerController customerController;
    private SearchController searchController;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;

    public SearchOrderCustomerPanel() {
        this.customerController = new CustomerController();
        this.searchController = new SearchController();
        this.customerComboBoxModel = new DefaultComboBoxModel<>();
        this.customerComboBox = new JComboBox<>(this.customerComboBoxModel);
        this.orderDetailTable = new JTable();
        this.customerErrorLabel = new JLabel();
        this.submitButton = new JButton("Soumettre");
        this.submitButton.addActionListener(this);

        // Initialiser les spinners de date
        SpinnerDateModel startDateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        this.startDateSpinner = new JSpinner(startDateModel);
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startDateEditor);

        SpinnerDateModel endDateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        this.endDateSpinner = new JSpinner(endDateModel);
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endDateEditor);

        this.setAllCustomers();

        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JLabel("Client: "));
        northPanel.add(customerComboBox);
        northPanel.add(new JLabel("Date de début: "));
        northPanel.add(startDateSpinner);
        northPanel.add(new JLabel("Date de fin: "));
        northPanel.add(endDateSpinner);
        northPanel.add(submitButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(orderDetailTable), BorderLayout.CENTER);
        this.add(customerErrorLabel, BorderLayout.SOUTH);
    }

    private void setAllCustomers() {
        try {
            ArrayList<Customer> customers = customerController.readAllCustomers();
            this.customerComboBoxModel.addAll(customers);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setResult(Customer customer, LocalDate startDate, LocalDate endDate) throws SQLException {
        Map<Integer, SearchOrderCustomerResult> searchOrderCustomerResults = this.searchController.searchEachOrderOfCustomer(customer.getNumber(), startDate, endDate);
        SearchOrderCustomerTableModel tableModel = new SearchOrderCustomerTableModel(searchOrderCustomerResults);
        this.orderDetailTable.setModel(tableModel);
        tableModel.fireTableDataChanged(); // Notifies the table model that the data has changed
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Submit button pressed"); // Debug message
        Customer selectedCustomer = (Customer) this.customerComboBox.getSelectedItem();
        Date startDateValue = (Date) this.startDateSpinner.getValue();
        Date endDateValue = (Date) this.endDateSpinner.getValue();

        if (selectedCustomer != null && startDateValue != null && endDateValue != null) {
            LocalDate startDate = convertToLocalDateViaInstant(startDateValue);
            LocalDate endDate = convertToLocalDateViaInstant(endDateValue);

            try {
                setResult(selectedCustomer, startDate, endDate);
                System.out.println("Results set successfully"); // Debug message
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des données", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vous devez sélectionner un client et des dates valides", "Erreur de sélection", JOptionPane.ERROR_MESSAGE);
            System.out.println("Invalid selection"); // Debug message
        }
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
