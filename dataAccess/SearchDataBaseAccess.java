package dataAccess;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


import exception.SearchSupplierHistoryException;
import interfaceAccess.SearchDataAccess;
import model.*;
import exception.*;
public class SearchDataBaseAccess implements SearchDataAccess {


    public ArrayList<SearchSupplierDetailResult> searchSupplierHistory(String supplierLegalName, LocalDate startDate, LocalDate endDate) throws SQLException, SearchSupplierHistoryException {
        ArrayList<SearchSupplierDetailResult> searchResults = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();

            String query = "SELECT sd.dateAndTime, sd.quantity, p.codeProduct AS product_code, p.labelProduct, c.categoryName " +
                    "FROM SupplierDetail sd " +
                    "INNER JOIN Product p ON sd.product = p.codeProduct " +
                    "INNER JOIN Category c ON p.category = c.categoryName " +
                    "WHERE sd.supplier = ? AND sd.dateAndTime BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, supplierLegalName);
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3, Date.valueOf(endDate));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LocalDate dateAndTime = resultSet.getDate("dateAndTime").toLocalDate();
                int quantity = resultSet.getInt("quantity");
                String labelProduct = resultSet.getString("labelProduct");
                String categoryName = resultSet.getString("categoryName");
                int productCode = resultSet.getInt("product_code");

                SearchSupplierDetailResult supplierDetailResult = new SearchSupplierDetailResult(dateAndTime, quantity, labelProduct, categoryName, productCode);
                searchResults.add(supplierDetailResult);
            }

            return searchResults;
        } catch (SQLException exception) {
            throw new SearchSupplierHistoryException(exception.getMessage());
        }

    }


//mettre les exceptions


    public Map<Integer, SearchOrderCustomerResult> searchEachOrderOfCustomer(int customerNumber, LocalDate startDate, LocalDate endDate) throws SQLException {
        Map<Integer, SearchOrderCustomerResult> searchResults = new HashMap<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT oc.codeOrder AS order_code, oc.dateAndTime AS order_dateAndTime, oc.methodOfPayement AS payment_method, " +
                    "c.lastName AS customer_lastName, c.firstName AS customer_firstName, " +
                    "ol.codeOrderLine AS orderLine_code, ol.quantity AS orderLine_quantity, " +
                    "p.codeProduct AS product_code, p.labelProduct AS product_label, p.category AS product_category, p.unitPriceExcludingTax AS product_price " +
                    "FROM orderCustomer oc " +
                    "INNER JOIN orderLine ol ON oc.codeOrder = ol.orderCustomer " +
                    "INNER JOIN product p ON ol.product = p.codeProduct " +
                    "INNER JOIN customer c ON oc.customer = c.numberCustomer " +
                    "WHERE oc.customer = ? AND oc.dateAndTime BETWEEN ? AND ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerNumber);
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3, Date.valueOf(endDate));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderCode = resultSet.getInt("order_code");
                String dateAndTime = resultSet.getString("order_dateAndTime");
                String paymentMethod = resultSet.getString("payment_method");

                SearchOrderLineResults orderLineResult = new SearchOrderLineResults(
                        resultSet.getInt("orderLine_code"),
                        resultSet.getInt("orderLine_quantity"),
                        resultSet.getString("product_label"),
                        resultSet.getFloat("product_price"),
                        resultSet.getString("product_category")
                );

                if (!searchResults.containsKey(orderCode)) {
                    ArrayList<SearchOrderLineResults> orderLinesResults = new ArrayList<>();
                    orderLinesResults.add(orderLineResult);
                    SearchOrderCustomerResult ordersCustomerResults = new SearchOrderCustomerResult(orderCode, dateAndTime, paymentMethod, orderLinesResults);
                    searchResults.put(orderCode, ordersCustomerResults);
                } else {
                    SearchOrderCustomerResult ordersCustomerResults = searchResults.get(orderCode);
                    ordersCustomerResults.getOrderLines().add(orderLineResult);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving order data", e);
        }

        return searchResults;
    }


    public Map<Integer, SearchOrderCustomerResults> searchEachOrderOfProduct(int productCode, LocalDate startDate, LocalDate endDate) throws SQLException {
        Map<Integer, SearchOrderCustomerResults> results = new HashMap<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT oc.codeOrder AS order_code, oc.dateAndTime AS order_date, oc.methodOfPayement, " +
                    "c.lastName, c.firstName, ol.codeOrderLine, ol.quantity, p.labelProduct, " +
                    "p.unitPriceExcludingTax, p.category " +
                    "FROM OrderCustomer oc " +
                    "JOIN OrderLine ol ON oc.codeOrder = ol.orderCustomer " +
                    "JOIN Product p ON ol.product = p.codeProduct " +
                    "JOIN Customer c ON oc.customer = c.numberCustomer " +
                    "WHERE p.codeProduct = ? AND oc.dateAndTime BETWEEN ? AND ?";


            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productCode);
            statement.setTimestamp(2, Timestamp.valueOf(startDate.atStartOfDay()));
            statement.setTimestamp(3, Timestamp.valueOf(endDate.atStartOfDay().plusDays(1).minusSeconds(1)));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderCode = resultSet.getInt("order_code");
                String orderDate = resultSet.getString("order_date");
                String methodOfPayement = resultSet.getString("methodOfPayement");
                String customerLastName = resultSet.getString("lastName");
                String customerFirstName = resultSet.getString("firstName");
                int orderLineCode = resultSet.getInt("codeOrderLine");
                int quantity = resultSet.getInt("quantity");
                String productLabel = resultSet.getString("labelProduct");
                float unitPriceExcludingTax = resultSet.getFloat("unitPriceExcludingTax");
                String productCategory = resultSet.getString("category");

                SearchOrderLineResult lineResult = new SearchOrderLineResult(orderLineCode, quantity, productLabel, unitPriceExcludingTax, productCategory, customerLastName, customerFirstName);

                if (!results.containsKey(orderCode)) {
                    results.put(orderCode, new SearchOrderCustomerResults(orderCode, orderDate, methodOfPayement, new ArrayList<>()));
                }
                results.get(orderCode).getOrderLines().add(lineResult);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la récupération des données: " + e.getMessage());
        }

        return results;
    }


    @Override
    public List<SalesSearchResult> searchSalesBetweenDates(LocalDate startDate, LocalDate endDate) throws SQLException {
        List<SalesSearchResult> salesResults = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT DATE(dateAndTime) AS saleDate, SUM(ol.quantity * p.unitPriceExcludingTax * (1 + p.vatRate / 100.0)) AS salesAmount " +
                    "FROM orderCustomer oc " +
                    "INNER JOIN orderLine ol ON oc.codeOrder = ol.orderCustomer " +
                    "INNER JOIN product p ON ol.product = p.codeProduct " +
                    "WHERE oc.dateAndTime BETWEEN ? AND ? " +
                    "GROUP BY saleDate";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(startDate));
            statement.setDate(2, Date.valueOf(endDate));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LocalDate date = resultSet.getDate("saleDate").toLocalDate();
                double salesAmount = resultSet.getDouble("salesAmount");
                SalesSearchResult result = new SalesSearchResult(date, salesAmount);
                salesResults.add(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la récupération des données: " + e.getMessage());
        }

        return salesResults;
    }
}
