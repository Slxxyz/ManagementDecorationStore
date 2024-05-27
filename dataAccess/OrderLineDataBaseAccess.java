package dataAccess;

import exception.*;
import model.OrderLine;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.OrderLineDataAccess;

public class OrderLineDataBaseAccess implements OrderLineDataAccess{
    @Override
    public void createOrderLine(OrderLine orderLine) throws OrderLineException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "INSERT INTO orderline VALUES (?,?,?,?);";
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
    public ArrayList<OrderLine> readAllOrderLines() throws OrderLineException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM orderline;";
            PreparedStatement statement = connexion.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
            while (data.next()) {
                int code = data.getInt("codeOrderLine");
                int quantity = data.getInt("quantity");
                int orderCustomer = data.getInt("orderCustomer");
                int product= data.getInt("product");
                OrderLine orderLine = new OrderLine(code,quantity,orderCustomer,product);
                orderLines.add(orderLine);
            }
            return orderLines;
        } catch (SQLException exception) {
            throw new OrderLineException(exception.getMessage(), new AllException(), new ReadException());
        }
    }


    @Override //lis toutes les orderLine d'un order
    public ArrayList<OrderLine> readAllOrderLinesFor(int orderLineCode) throws OrderLineException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM orderline WHERE code = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderLineCode);
            ResultSet data = statement.executeQuery();
            ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
            while (data.next()) {
                int code = data.getInt("codeOrderLine");
                int quantity = data.getInt("quantity");
                int orderCustomer = data.getInt("orderCustomer");
                int product= data.getInt("product");
                OrderLine orderLine = new OrderLine(code,quantity,orderCustomer,product);
                orderLines.add(orderLine);
            }
            return orderLines;
        } catch (SQLException exception) {
            throw new OrderLineException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    //supprime toutes les orderLine d'un order
    @Override
    public void deleteAllOrderLinesFor(int orderCode) throws OrderLineException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "DELETE FROM orderline WHERE orderCustomer = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderCode);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new OrderLineException(exception.getMessage(), new AllException(), new DeleteException());
        }
    }

    //retourne le prochain code d'orderLine
    @Override
    public int getNextCode() throws NextCodeOrderLineException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT codeOrderLine FROM orderline ORDER BY codeOrderLine DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("codeOrderLine") + 1;
        } catch (SQLException exception) {
            throw new NextCodeOrderLineException(exception.getMessage());
        }
    }

}