package interfaceAccess;

import exception.OrderCustomerException;
import model.OrderCustomer;
import java.util.ArrayList;
public interface OrderCustomerDataAccess {
    //create
    public void createOrderCustomer(OrderCustomer orderCustomer) throws OrderCustomerException;
    //read
    public OrderCustomer readOrderCustomer(int orderCustomerId) throws OrderCustomerException;
    //delete All Order for a customer
    public void deleteAllOrderCustomerFor(int customerNumber) throws OrderCustomerException;

    //readAll
    public ArrayList<OrderCustomer> readAllOrderCustomers() throws OrderCustomerException;
    //readAllOrderCustomerFor
    public ArrayList<OrderCustomer> readAllOrderCustomerFor(int customerNumber) throws OrderCustomerException;

}
