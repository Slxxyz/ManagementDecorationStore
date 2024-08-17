package userInterface;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import controller.SearchController;

import model.SalesSearchResult;


public class SalesSearchPanel extends JPanel {
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JButton searchButton;
    private JTable resultsTable;

    public SalesSearchPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Date de début:"));
        startDateSpinner = createDateSpinner();
        inputPanel.add(startDateSpinner);

        inputPanel.add(new JLabel("Date de fin:"));
        endDateSpinner = createDateSpinner();
        inputPanel.add(endDateSpinner);

        searchButton = new JButton("Rechercher");
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.NORTH);

        resultsTable = new JTable();
        add(new JScrollPane(resultsTable), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
    }

    private JSpinner createDateSpinner() {
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        return dateSpinner;
    }

    private void performSearch() {
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();

        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        SearchController dataAccess = new SearchController();

        try {
            List<SalesSearchResult> results = dataAccess.searchSalesBetweenDates(startLocalDate, endLocalDate);
            TableModel tableModel = new SalesSearchTableModel(results);
            resultsTable.setModel(tableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
