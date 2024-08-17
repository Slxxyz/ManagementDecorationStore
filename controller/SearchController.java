package controller;


import business.SearchManager;
import exception.SearchSupplierHistoryException;
import model.SalesSearchResult;
import model.SearchOrderCustomerResult;
import model.SearchOrderCustomerResults;
import model.SearchSupplierDetailResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
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


    public Map<Integer, SearchOrderCustomerResult> searchEachOrderOfCustomer(int number, LocalDate startDate, LocalDate endDate)throws SQLException {
        return this.manager.searchEachOrderOfCustomer(number,startDate,endDate);
    }

    public Map<Integer, SearchOrderCustomerResults> searchEachOrderOfProduct(int productCode, LocalDate startDate, LocalDate endDate) throws SQLException{
        return this.manager.searchEachOrderOfProduct(productCode,startDate,endDate);
    }


    public List<SalesSearchResult> searchSalesBetweenDates(LocalDate startDate, LocalDate endDate) throws SQLException{
        return this.manager.searchSalesBetweenDates(startDate,endDate);

    }

}
