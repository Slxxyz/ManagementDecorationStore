package userInterface;

import controller.ProductController;
import controller.SearchController;
import model.Product;
import model.SearchOrderCustomerResults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import  java.util.*;

public class SearchOrderProductPanel extends JPanel implements ActionListener {
    private JComboBox<Product> productComboBox;
    private DefaultComboBoxModel<Product> productComboBoxModel;
    private JLabel productErrorLabel;
    private JTable orderDetailTable;
    private JButton submitButton;
    private ProductController productController;
    private SearchController searchController;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;

    public SearchOrderProductPanel() {
        this.productController = new ProductController();
        this.searchController = new SearchController();
        this.productComboBoxModel = new DefaultComboBoxModel<>();
        this.productComboBox = new JComboBox<>(this.productComboBoxModel);
        this.orderDetailTable = new JTable();
        this.productErrorLabel = new JLabel();
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

        this.setAllProducts();

        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JLabel("Produit: "));
        northPanel.add(productComboBox);
        northPanel.add(new JLabel("Date de début: "));
        northPanel.add(startDateSpinner);
        northPanel.add(new JLabel("Date de fin: "));
        northPanel.add(endDateSpinner);
        northPanel.add(submitButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(orderDetailTable), BorderLayout.CENTER);
        this.add(productErrorLabel, BorderLayout.SOUTH);
    }

    private void setAllProducts() {
        try {
            ArrayList<Product> products = productController.readAllProducts();
            this.productComboBoxModel.addAll(products);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setResult(Product product, LocalDate startDate, LocalDate endDate) throws SQLException {
        Map<Integer, SearchOrderCustomerResults> searchOrderProductResults = this.searchController.searchEachOrderOfProduct(product.getCode(), startDate, endDate);
        SearchOrderProductTableModel tableModel = new SearchOrderProductTableModel(searchOrderProductResults);
        this.orderDetailTable.setModel(tableModel);
        tableModel.fireTableDataChanged(); // Notifies the table model that the data has changed
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Submit button pressed"); // Debug message
        Product selectedProduct = (Product) this.productComboBox.getSelectedItem();
        Date startDateValue = (Date) this.startDateSpinner.getValue();
        Date endDateValue = (Date) this.endDateSpinner.getValue();

        if (selectedProduct != null && startDateValue != null && endDateValue != null) {
            LocalDate startDate = convertToLocalDateViaInstant(startDateValue);
            LocalDate endDate = convertToLocalDateViaInstant(endDateValue);

            try {
                setResult(selectedProduct, startDate, endDate);
                System.out.println("Results set successfully"); // Debug message
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                System.out.println("SQLException: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vous devez sélectionner un produit et des dates valides", "Erreur de sélection", JOptionPane.ERROR_MESSAGE);
            System.out.println("Invalid selection"); // Debug message
        }
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
