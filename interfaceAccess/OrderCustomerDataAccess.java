package interfaceAccess;

import exception.NextCodeCustomerException;
import exception.OrderCustomerException;
import exception.NextCodeOrderCustomerException;
import exception.NumberOrderCustomerException;
import model.OrderCustomer;
import java.util.ArrayList;
public interface OrderCustomerDataAccess {
    //create
    public void createOrderCustomer(OrderCustomer orderCustomer) throws OrderCustomerException;
    //read
    public OrderCustomer readOrderCustomer(int orderCustomerId) throws OrderCustomerException;

    //delete
    public void deleteOrderCustomer(int orderCustomerId) throws OrderCustomerException;

    //delete All Order for a customer
    public void deleteAllOrderCustomerFor(int customerNumber) throws OrderCustomerException;

    //readAll
    public ArrayList<OrderCustomer> readAllOrderCustomers() throws OrderCustomerException;
    //readAllOrderCustomerFor
    public ArrayList<OrderCustomer> readAllOrderCustomerFor(int customerNumber) throws OrderCustomerException;
    //getNextCode
    public int getNextCode() throws NextCodeOrderCustomerException;
    //getNumberOrderCustomer
    public int getNumberOrderCustomer() throws NumberOrderCustomerException;
    //getLastOrderCustomer
    public int getLastOrderCustomerId() throws NextCodeOrderCustomerException;

}
