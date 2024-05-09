package business;

import interfaceAccess.OrderCustomerDataAccess;
import dataAccess.OrderCustomerDataBaseAccess;
import model.OrderCustomer;
import exception.OrderCustomerException;
import java.util.ArrayList;

public class OrderCustomerManager {
    private OrderCustomerDataAccess customerAccess;

    public OrderCustomerManager(){
        setOrderCustomerManager(new OrderCustomerDataBaseAccess());
    }
    public void setOrderCustomerManager(OrderCustomerDataAccess customerAccess){
        this.customerAccess = customerAccess;
    }

    //create
    public void createOrderCustomer(OrderCustomer orderCustomer) throws OrderCustomerException{
        this.customerAccess.createOrderCustomer(orderCustomer);
    }
    //read
    public OrderCustomer readOrderCustomer(int orderCustomerId) throws OrderCustomerException{
        return this.customerAccess.readOrderCustomer(orderCustomerId);
    }
    //delete All Order for a customer
    public void deleteAllOrderCustomerFor(int customerNumber) throws OrderCustomerException{
        this.customerAccess.deleteAllOrderCustomerFor(customerNumber);
    }
    //readAll
    public ArrayList<OrderCustomer> readAllOrderCustomers() throws OrderCustomerException{
        return this.customerAccess.readAllOrderCustomers();
    }
    //readAllOrderCustomerFor
    public ArrayList<OrderCustomer> readAllOrderCustomerFor(int customerNumber) throws OrderCustomerException{
        return this.customerAccess.readAllOrderCustomerFor(customerNumber);
    }
}