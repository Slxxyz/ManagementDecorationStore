package dataAccess;

import exception.*;
import model.OrderLine;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.OrderLineDataAccess;


public class OrderLineDataBaseAccess {
    @Override
    public void createOrderLine(OrderLine orderLine) throws OrderLineException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "INSERT INTO orderLine VALUES (?,?,?,?);";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1, orderLine.getCode());
            statement.setInt(2, orderLine.getQuantity());
            statement.setInt(3, orderLine.getOrderCustomer());
            statement.setInt(4, orderLine.getProduct());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderLineException(exception.getMessage(), new OneException(), new CreateException());
        }
    }


    @Override// lis TOUTES les orderLine de la BD
    public ArrayList<OrderLine> readAllOrderLine() throws OrderLineException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM orderLine;";
            PreparedStatement statement = connexion.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
            while (data.next()) {
                int code = data.getInt("code");
                Integer quantity = data.getInt("quantity");
                Integer orderCustomer = data.getInt("orderCustomer");
                Integer product= data.getInt("product");
                OrderLine orderLine = new OrderLine(code,quantity,orderCustomer,product);
                orderLines.add(orderLine);
            }
            return orderLines;
        } catch (SQLException exception) {
            throw new OrderLinesException(exception.getMessage(), new AllException(), new ReadException());
        }
    }


    @Override //lis tte les order d'un Customer
    public ArrayList<OrderLine> readAllOrderLineFor(Integer orderLineCode) throws OrderLineException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM orderLine WHERE code = ?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1, orderLineCode);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
            while (data.next()) {
                int code = data.getInt("code");
                Integer quantity = data.getInt("quantity");
                Integer orderCustomer = data.getInt("orderCustomer");
                Integer product= data.getInt("product");
                OrderLine orderLine = new OrderLine(code,quantity,orderCustomer,product);
                orderLines.add(orderLine);
            }
            return orderLines;
        } catch (SQLException exception) {
            throw new OrderLineException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

}
