package business;
import exception.SearchSupplierHistoryException;
import model.SearchSupplierDetailResult;
import interfaceAccess.SearchDataAccess;
import dataAccess.SearchDataBaseAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchManager {
    private SearchDataAccess searchDataAccess;

    public SearchManager() {
        setSearchDataAcces(new SearchDataBaseAccess());
    }

    public void setSearchDataAcces(SearchDataAccess searchDataAccess) {
        this.searchDataAccess = searchDataAccess;
    }


    public ArrayList<SearchSupplierDetailResult> searchSupplierHistory(String supplierLegalName, LocalDate startDate, LocalDate endDate) throws SQLException, SearchSupplierHistoryException {
        return searchDataAccess.searchSupplierHistory(supplierLegalName,startDate,endDate);
    }

}
