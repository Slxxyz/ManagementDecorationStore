package controller;

import business.OrderCustomerManager;
import exception.OrderCustomerException;
import model.OrderCustomer;
import java.util.ArrayList;
public class OrderCustomerController {
    private OrderCustomerManager manager;
    public OrderCustomerController() {
        setManager(new OrderCustomerManager());
    }
    public void setManager(OrderCustomerManager manager) {
        this.manager = manager;
    }
    //create
    public void createOrderCustomer(OrderCustomer orderCustomer)throws OrderCustomerException {
        this.manager.createOrderCustomer(orderCustomer);
    }
    //read
    public OrderCustomer readOrderCustomer(int orderCustomerId)throws OrderCustomerException {
        return this.manager.readOrderCustomer(orderCustomerId);
    }
    //delete All Order for a customer
    public void deleteAllOrderCustomerFor(int customerNumber)throws OrderCustomerException {
        this.manager.deleteAllOrderCustomerFor(customerNumber);
    }
    //readAll
    public ArrayList<OrderCustomer> readAllOrderCustomers() throws OrderCustomerException {
        return this.manager.readAllOrderCustomers();
    }
    //readAllOrderCustomerFor
    public ArrayList<OrderCustomer> readAllOrderCustomerFor(int customerNumber) throws OrderCustomerException {
        return this.manager.readAllOrderCustomerFor(customerNumber);
    }

}
