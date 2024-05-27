package controller;

import business.CustomerManager;
import exception.CustomerException;
import exception.NextCodeCustomerException;
import exception.NumberCustomerException;
import model.Customer;

import java.util.ArrayList;

public class CustomerController {
    private CustomerManager manager;

    public CustomerController() {
        setManager(new CustomerManager());
    }
    public void setManager(CustomerManager manager) {
        this.manager = manager;
    }
    //create
    public void createCustomer(Customer customer)throws CustomerException {
        this.manager.createCustomer(customer);
    }
    //read
    public Customer readCustomer(int numberCustomer)throws CustomerException {
        return this.manager.readCustomer(numberCustomer);
    }
    //update
    public void updateCustomer(Customer customer)throws CustomerException {
        this.manager.updateCustomer(customer);
    }
    //delete
    public void deleteCustomer(int numberCustomer)throws CustomerException {
        this.manager.deleteCustomer(numberCustomer);
    }
    //readAll
    public ArrayList<Customer> readAllCustomers() throws CustomerException {
        return this.manager.readAllCustomers();
    }
    //getNextCode
    public int getNextCode() throws NextCodeCustomerException {
        return this.manager.getNextCode();
    }
    //getNumberCustomer
    public int getNumberCustomer() throws NumberCustomerException{
        return this.manager.getNumberCustomer();
    }
    //getLastCustomerId
    public int getLastCustomerId() throws NextCodeCustomerException {
        return this.manager.getLastCustomerId();
    }
}
