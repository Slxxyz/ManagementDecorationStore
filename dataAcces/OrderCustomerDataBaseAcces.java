package dataAccess;

import exception.*;
import model.OrderCustomer;
import java.sql.*;
import java.util.ArrayList;

public class OrderCustomerDataBaseAccess {

    public void createOrderCustomer(OrderCustomer orderCustomer) throws OrderCustomerException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "INSERT INTO orderCustomer VALUES (?,?,?,?);";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1, orderCustomer.getCode());
            //ICI ON NE TRAITE QUE LA DATE MAIS PAS LE TIME ! il faut trouver une solution
            statement.setTimestamp(2, orderCustomer.getDateAndTime());
            statement.setString(3, orderCustomer.getMethodOfPayment());
            statement.setInt(4, orderCustomer.getCustomer());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new OneException(), new CreateException());
        }
    }

    @Override
    public void deleteOrderCustomer(Integer orderCode) throws OrderCustomerException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "DELETE FROM orderCustomer WHERE code = ?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1, orderCode);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }


    @Override  //supprime tte les ordrer d'un customer
    public void deleteAllOrderCustomer(Integer customerNumber) throws OrderCustomerException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "DELETE FROM orderCustomer WHERE customer = ?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1, customerNumber);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new AllException(), new DeleteException());
        }
    }

    @Override// lis TOUTES les orderCustomer de la BD
    public ArrayList<OrderCustomer> readAllOrderCustomer() throws OrderCustomerException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM orderCustomer;";
            PreparedStatement statement = connexion.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderCustomer> ordersCustomer = new ArrayList<OrderCustomer>();
            while (data.next()) {
                int code = data.getInt("code");
                Timestamp dateAndTime = data.getTimestamp("dateAndTime");
                String methodOfPayement = data.getString("methodOfPayement");
                int customer = data.getInt("customer");
                OrderCustomer orderCustomer = new OrderCustomer(code,dateAndTime,methodOfPayement,customer);
                ordersCustomer.add(orderCustomer);
            }
            return ordersCustomer;
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override //lis tte les order d'un Customer
    public ArrayList<OrderCustomer> readAllOrderCustomerFor(Integer customerNumber) throws OrderCustomerException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM orderCustomer WHERE customer = ?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1, customerNumber);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderCustomer> ordersCustomer = new ArrayList<OrderCustomer>();
            while (data.next()) {
                int code = data.getInt("code");
                Timestamp dateAndTime = data.getTimestamp("dateAndTime");
                String methodOfPayement = data.getString("methodOfPayement");
                int customer = data.getInt("customer");
                OrderCustomer orderCustomer = new OrderCustomer(code,dateAndTime,methodOfPayement,customer);
                ordersCustomer.add(orderCustomer);
            }
            return ordersCustomer;
        } catch (SQLException exception) {
            throw new OrderCustomerException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override
    public OrderCustomer readOrderCustomer(int codeOrderCustomer) throws OrderCustomerException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM orderCustomer WHERE code = ?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1, codeOrderCustomer);
            ResultSet data = statement.executeQuery();
            data.next();
            int code = data.getInt("code");
            Timestamp dateAndTime = data.getTimestamp("dateAndTime");
            String methodOfPayement = data.getString("methodOfPayement");
            int customer = data.getInt("customer");
            return new OrderCustomer(code,dateAndTime,methodOfPayement,customer);
        } catch (Exception exception) {
            throw new OrderCustomerException(exception.getMessage(), new OneException(), new ReadException());
        }
    }
}
