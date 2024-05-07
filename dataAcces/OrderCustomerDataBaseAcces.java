package dataAccess;

import exception.*;
import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.CustomerDataAccess;


public class CustomerDataBaseAccess {
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
                statement.setNull(6, Types.NULL);
            }

            String mailAddress = customer.getMailAddress();
            if (mailAddress != null) {
                statement.setString(7, mailAddress);
            } else {
                statement.setNull(7, Types.NULL);
            }
            statement.setDate(8, Date.valueOf(customer.getBirthday()));
            statement.setBoolean(9,customer.getIsMarried());
            statement.setInt(10,customer.getCityId());
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new CreateException());
        }
    }


    //je ne comprends pas cette fonction pour le moment
    @Override
    public Customer readCustomer() throws CustomerException {
        try {
            throw new SQLException();
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
                        poinstNb = ?,
                        telNumber = ?, 
                        mailAddress = ?, 
                        birthday = ?, 
                        isMarried = ?, 
                        cityId = ?,
                    WHERE number = ?;
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
                statement.setNull(5, Types.NULL);
            }

            String mailAddress = customer.getMailAddress();
            if (mailAddress != null) {
                statement.setString(6, mailAddress);
            } else {
                statement.setNull(6, Types.NULL);
            }

            statement.setDate(7,Date.valueOf(customer.getBirthday()));

            statement.setBoolean(8,customer.getIsMarried());
            statement.setInt(9,customer.getCityId());
            statement.setInt(10,customer.getNumber());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }

    @Override
    public void deleteCustomer(int numberCustomer) throws CustomerException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String query = "DELETE FROM customer WHERE number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,numberCustomer);
            statement.executeUpdate();

        } catch(SQLException exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }

    @Override
    public ArrayList<Customer> readAllCustomer() throws CustomerException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM customer;";
            PreparedStatement statement = connexion.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<Customer>();

            while (data.next()) {
                int number = data.getInt("number");
                String lastName = data.getString("lastName");
                String firstName = data.getString("firstName");
                String gender = data.getString("gender");
                //il utilise ici Integer pour les autres données int, il faut comprendre pourquoi !!
                Integer pointsNb = data.getInt("pointsNb");
                Date birthday = data.getDate("birthday");
                boolean isMarried = data.getBoolean("isMarried ");
                Integer cityId = data.getInt("cityId");

                Customer customer = new Customer(number, lastName, firstName, gender, pointsNb, birthday.toLocalDate(), isMarried, cityId);

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
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT MAX(number) AS 'NEXT_CODE' FROM customer;";
            PreparedStatement statement = connexion.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("NEXT_NUMBER") + 1;
        } catch (SQLException exception) {
            throw new NextCodeCustomerException(exception.getMessage());
        }
    }

    @Override
    public int getNumberCustomer() throws NumberCustomerException {
        try{
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT COUNT(*) AS 'NUMBER_CUSTOMER' FROM customer;";
            PreparedStatement statement = connexion.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("NUMBER_CUSTOMER");
        }catch(SQLException exception){
            throw new NumberCustomerException(exception.getMessage());
        }
    }
}

