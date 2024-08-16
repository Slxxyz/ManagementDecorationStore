package userInterface;

import controller.SearchController;
import controller.SupplierController;
import controller.SupplierDetailController;
import exception.SearchSupplierHistoryException;
import exception.SupplierException;
import model.Supplier;
import model.SearchSupplierDetailResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchSupplierHistoryPanel extends JPanel implements ActionListener {
    private JComboBox<Supplier> supplierComboBox;
    private DefaultComboBoxModel<Supplier> supplierComboBoxModel;
    private JLabel supplierErrorLabel;
    private JTable supplierDetailTable;
    private JButton submitButton;
    private SupplierController supplierController;
    private SupplierDetailController supplierDetailController;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private SearchController searchController;

    public SearchSupplierHistoryPanel() {
        this.supplierController = new SupplierController();
        this.supplierDetailController = new SupplierDetailController();
        this.searchController = new SearchController();
        this.supplierComboBoxModel = new DefaultComboBoxModel<>();
        this.supplierComboBox = new JComboBox<>(this.supplierComboBoxModel);
        this.supplierDetailTable = new JTable();
        this.supplierErrorLabel = new JLabel();
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

        this.setAllSuppliers();

        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JLabel("Fournisseur: "));
        northPanel.add(supplierComboBox);
        northPanel.add(new JLabel("Date de début: "));
        northPanel.add(startDateSpinner);
        northPanel.add(new JLabel("Date de fin: "));
        northPanel.add(endDateSpinner);
        northPanel.add(submitButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(supplierDetailTable), BorderLayout.CENTER);
        this.add(supplierErrorLabel, BorderLayout.SOUTH);
    }

    private void setAllSuppliers() {
        try {
            ArrayList<Supplier> suppliers = supplierController.readAllSupplier();
            this.supplierComboBoxModel.addAll(suppliers);
        } catch (SupplierException exception) {
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setResult(Supplier supplier, LocalDate startDate, LocalDate endDate) throws SQLException, SearchSupplierHistoryException {
        ArrayList<SearchSupplierDetailResult> searchSupplierDetailResults = this.searchController.searchSupplierHistory(supplier.getLegalName(), startDate, endDate);
        SearchSupplierHistoryTableModel tableModel = new SearchSupplierHistoryTableModel(searchSupplierDetailResults);
        this.supplierDetailTable.setModel(tableModel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Supplier selectedSupplier = (Supplier) this.supplierComboBox.getSelectedItem();
        Date startDateValue = (Date) this.startDateSpinner.getValue();
        Date endDateValue = (Date) this.endDateSpinner.getValue();

        if (selectedSupplier != null && startDateValue != null && endDateValue != null) {
            LocalDate startDate = convertToLocalDateViaInstant(startDateValue);
            LocalDate endDate = convertToLocalDateViaInstant(endDateValue);
            try {
                setResult(selectedSupplier, startDate, endDate);
            } catch (SQLException | SearchSupplierHistoryException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vous devez sélectionner un fournisseur et des dates valides", "Erreur de sélection", JOptionPane.ERROR_MESSAGE);
        }
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
