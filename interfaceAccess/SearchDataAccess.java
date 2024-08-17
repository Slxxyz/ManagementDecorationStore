package interfaceAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

import exception.SearchSupplierHistoryException;
import model.*;

public interface SearchDataAccess {

    ArrayList<SearchSupplierDetailResult> searchSupplierHistory( String supplierLegalName, LocalDate startDate, LocalDate endDate) throws SQLException, SearchSupplierHistoryException;

    Map<Integer, SearchOrderCustomerResult> searchEachOrderOfCustomer(int number, LocalDate startDate, LocalDate endDate) throws SQLException;

    Map<Integer, SearchOrderCustomerResults> searchEachOrderOfProduct(int productCode, LocalDate startDate, LocalDate endDate) throws SQLException;

    List<SalesSearchResult> searchSalesBetweenDates(LocalDate startDate, LocalDate endDate) throws SQLException;

}
