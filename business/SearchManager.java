package business;
import exception.SearchSupplierHistoryException;
import model.SalesSearchResult;
import model.SearchOrderCustomerResult;
import model.SearchOrderCustomerResults;
import model.SearchSupplierDetailResult;
import interfaceAccess.SearchDataAccess;
import dataAccess.SearchDataBaseAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, SearchOrderCustomerResult> searchEachOrderOfCustomer(int number, LocalDate startDate, LocalDate endDate) throws SQLException{
        return searchDataAccess.searchEachOrderOfCustomer(number,startDate,endDate);
    }

    public Map<Integer, SearchOrderCustomerResults> searchEachOrderOfProduct(int productCode, LocalDate startDate, LocalDate endDate) throws SQLException{
        return searchDataAccess.searchEachOrderOfProduct(productCode,startDate,endDate);
    }

    public List<SalesSearchResult> searchSalesBetweenDates(LocalDate startDate, LocalDate endDate)throws SQLException{
        return searchDataAccess.searchSalesBetweenDates(startDate,endDate);

    }

}
