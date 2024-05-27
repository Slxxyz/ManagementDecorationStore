package dataAccess;

import exception.*;
import model.Customer;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import interfaceAccess.CustomerDataAccess;

import javax.swing.*;


public class CustomerDataBaseAccess implements CustomerDataAccess{
    //create
    @Override
    public void createCustomer(Customer customer) throws CustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query =
                    """
                    INSERT INTO customer
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,customer.getNumber());
            statement.setString(2,customer.getLastName());
            statement.setString(3,customer.getFirstName());
            statement.setString(4,customer.getGender());
            statement.setInt(5,customer.getPointNb());

            String telNumber = customer.getTelNumber();
            if (telNumber != null) {
                statement.setString(6, telNumber);
            } else {
                statement.setNull(6, Types.VARCHAR);
            }

            String mailAddress = customer.getMailAddress();
            if (mailAddress != null) {
                statement.setString(7, mailAddress);
            } else {
                statement.setNull(7, Types.VARCHAR);
            }
            statement.setDate(8, Date.valueOf(customer.getBirthday()));
            statement.setBoolean(9,customer.getIsMarried());
            statement.setInt(10,customer.getCityId());
            statement.executeUpdate();

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Attention !!!", JOptionPane.ERROR_MESSAGE);
        }
    }


    //read
    @Override
    public Customer readCustomer(int numberCustomer) throws CustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM customer WHERE numberCustomer = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, numberCustomer);
            ResultSet data = statement.executeQuery();
            data.next();
            int number = data.getInt("numberCustomer");
            String lastName = data.getString("lastName");
            String firstName = data.getString("firstName");
            String gender = data.getString("gender");
            int pointsNb = data.getInt("pointNb");
            LocalDate birthday = data.getObject("birthday", LocalDate.class);
            boolean isMarried = data.getBoolean("isMarried");
            int cityId = data.getInt("cityId");
            Customer customer = new Customer(number, lastName, firstName,gender,pointsNb,birthday,isMarried,cityId);

            String telNumber = data.getString("telNumber");
            if (!data.wasNull()) {
                customer.setTelNumber(telNumber);
            }
            String mailAddress = data.getString("mailAddress");
            if (!data.wasNull()) {
                customer.setMailAddress(mailAddress);
            }
            return customer;

        } catch (SQLException exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new ReadException());
        }

    }
    @Override
    public void updateCustomer(Customer customer) throws CustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query =
                    """
                    UPDATE customer 
                    SET 
                        lastName = ?,
                        firstName = ?,
                        gender = ?,
                        pointNb = ?,
                        telNumber = ?,
                        mailAddress = ?,
                        birthday = ?,
                        isMarried = ?,
                        cityId = ?
                    WHERE numberCustomer = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,customer.getLastName());
            statement.setString(2,customer.getFirstName());
            statement.setString(3,customer.getGender());
            statement.setInt(4,customer.getPointNb());

            String telNumber = customer.getTelNumber();
            if (telNumber != null) {
                statement.setString(5, telNumber);
            } else {
                statement.setNull(5, Types.VARCHAR);
            }

            String mailAddress = customer.getMailAddress();
            if (mailAddress != null) {
                statement.setString(6, mailAddress);
            } else {
                statement.setNull(6, Types.VARCHAR);
            }
            statement.setDate(7,java.sql.Date.valueOf(customer.getBirthday()));
            statement.setBoolean(8,customer.getIsMarried());
            statement.setInt(9,customer.getCityId());
            statement.setInt(10,customer.getNumber());
            statement.executeUpdate();

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Attention !!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteCustomer(int numberCustomer) throws CustomerException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String query = "DELETE FROM customer WHERE numberCustomer = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,numberCustomer);
            statement.executeUpdate();

        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Attention !!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public ArrayList<Customer> readAllCustomers() throws CustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM customer;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<Customer>();

            while (data.next()) {
                int number = data.getInt("numberCustomer");
                String lastName = data.getString("lastName");
                String firstName = data.getString("firstName");
                String gender = data.getString("gender");
                int pointsNb = data.getInt("pointNb");
                LocalDate birthday = data.getObject("birthday", LocalDate.class);
                boolean isMarried = data.getBoolean("isMarried");
                int cityId = data.getInt("cityId");

                Customer customer = new Customer(number, lastName, firstName, gender, pointsNb, birthday, isMarried, cityId);

                String telNumber = data.getString("telNumber");
                if (!data.wasNull()) {
                    customer.setTelNumber(telNumber);
                }

                String mailAddress = data.getString("mailAddress");
                if (!data.wasNull()) {
                    customer.setMailAddress(mailAddress);
                }

                customers.add(customer);
            }
            return customers;
        } catch (SQLException exception) {
            throw new CustomerException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override
    public int getNextCode() throws NextCodeCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT numberCustomer FROM customer ORDER BY numberCustomer DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("numberCustomer")+1;

        } catch (SQLException exception) {
            throw new NextCodeCustomerException(exception.getMessage());
        }
    }


    @Override
    public int getNumberCustomer() throws NumberCustomerException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT COUNT(*) AS 'NUMBER_CUSTOMER' FROM customer;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("NUMBER_CUSTOMER");
        }catch(SQLException exception){
            throw new NumberCustomerException(exception.getMessage());
        }
    }

    @Override
    public int getLastCustomerId() throws NextCodeCustomerException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT numberCustomer FROM customer ORDER BY numberCustomer DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
           data.next();
            return data.getInt("numberCustomer");

        } catch (SQLException exception) {
            throw new NextCodeCustomerException(exception.getMessage());
        }
    }
}
