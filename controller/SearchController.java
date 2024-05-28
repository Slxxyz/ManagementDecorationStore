package controller;


import business.SearchManager;
import exception.SearchSupplierHistoryException;
import model.SearchSupplierDetailResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchController {

    private SearchManager manager;

    public SearchController() {
        setManager(new SearchManager());
    }

    public void setManager(SearchManager manager) {
        this.manager = manager;
    }

    public ArrayList<SearchSupplierDetailResult> searchSupplierHistory(String supplierLegalName, LocalDate startDate, LocalDate endDate) throws SQLException, SearchSupplierHistoryException {
        return this.manager.searchSupplierHistory(supplierLegalName,startDate,endDate);
    }

}
