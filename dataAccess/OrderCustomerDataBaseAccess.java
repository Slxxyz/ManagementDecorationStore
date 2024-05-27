package dataAccess;

import exception.*;
import model.OrderCustomer;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.OrderCustomerDataAccess;

public class OrderCustomerDataBaseAccess implements OrderCustomerDataAccess {

    @Override
    public void createOrderCustomer(OrderCustomer orderCustomer) throws OrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "INSERT INTO ordercustomer VALUES (?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderCustomer.getCode());
            statement.setTimestamp(2, orderCustomer.getDateAndTime());
            statement.setString(3, orderCustomer.getMethodOfPayment());
            statement.setInt(4, orderCustomer.getCustomer());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new OneException(), new CreateException());
        }
    }
    @Override
    public OrderCustomer readOrderCustomer(int codeOrderCustomer) throws OrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM ordercustomer WHERE codeOrder = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codeOrderCustomer);
            ResultSet data = statement.executeQuery();
            data.next();
            int code = data.getInt("codeOrder");
            Timestamp dateAndTime = data.getTimestamp("dateAndTime");
            String methodOfPayment = data.getString("methodOfPayement");
            int customer = data.getInt("customer");
            return new OrderCustomer(code,dateAndTime,methodOfPayment,customer);
        } catch (Exception exception) {
            throw new OrderCustomerException(exception.getMessage(), new OneException(), new ReadException());
        }
    }

    @Override
    public void deleteOrderCustomer(int codeOrderCustomer) throws OrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "DELETE FROM ordercustomer WHERE codeOrder = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codeOrderCustomer);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }

    //delete All Order for a customer
    @Override
    public void deleteAllOrderCustomerFor(int customerNumber) throws OrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "DELETE FROM ordercustomer WHERE customer = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerNumber);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new AllException(), new DeleteException());
        }
    }

    @Override// lis TOUTES les orderCustomer de la BD
    public ArrayList<OrderCustomer> readAllOrderCustomers() throws OrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM ordercustomer;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderCustomer> ordersCustomer = new ArrayList<OrderCustomer>();
            while (data.next()) {
                int code = data.getInt("codeOrder");
                Timestamp dateAndTime = data.getTimestamp("dateAndTime");
                String methodOfPayment = data.getString("methodOfPayement");
                int customer = data.getInt("customer");
                OrderCustomer orderCustomer = new OrderCustomer(code,dateAndTime,methodOfPayment,customer);
                ordersCustomer.add(orderCustomer);
            }
            return ordersCustomer;
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override //lis tte les order d'un Customer
    public ArrayList<OrderCustomer> readAllOrderCustomerFor(int customerNumber) throws OrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM ordercustomer WHERE customer=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerNumber);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderCustomer> ordersCustomer = new ArrayList<OrderCustomer>();
            while (data.next()) {
                int code = data.getInt("codeOrder");
                Timestamp dateAndTime = data.getTimestamp("dateAndTime");
                String methodOfPayment = data.getString("methodOfPayement");
                int customer = data.getInt("customer");
                OrderCustomer orderCustomer = new OrderCustomer(code,dateAndTime,methodOfPayment,customer);
                ordersCustomer.add(orderCustomer);
            }
            return ordersCustomer;
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override
    public int getNextCode() throws NextCodeOrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT codeOrder FROM ordercustomer ORDER BY codeOrder DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("codeOrder")+1;

        } catch (SQLException exception) {
            throw new NextCodeOrderCustomerException(exception.getMessage());
        }
    }

    @Override
    public int getNumberOrderCustomer() throws NumberOrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT COUNT(*) AS 'NUMBER_ORDER_CUSTOMER' FROM ordercustomer;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("NUMBER_ORDER_CUSTOMER");
        } catch (SQLException exception) {
            throw new NumberOrderCustomerException(exception.getMessage());
        }
    }
    // LastOrderCustomerCode
    @Override
    public int getLastOrderCustomerId() throws NextCodeOrderCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT codeOrder FROM ordercustomer ORDER BY codeOrder DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("codeOrder");

        } catch (SQLException exception) {
            throw new NextCodeOrderCustomerException(exception.getMessage());
        }
    }

}