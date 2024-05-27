package business;

import dataAccess.CustomerDataBaseAccess;
import exception.NextCodeCustomerException;
import exception.NumberCustomerException;
import interfaceAccess.CustomerDataAccess;
import exception.CustomerException;
import model.Customer;
import java.util.ArrayList;

public class CustomerManager {
private CustomerDataAccess customerAccess;
    public CustomerManager(){
        setCustomerManager(new CustomerDataBaseAccess());
    }
    public void setCustomerManager(CustomerDataAccess customerAccess){
        this.customerAccess = customerAccess;
    }
    //create
    public void createCustomer(Customer customer) throws CustomerException{
        this.customerAccess.createCustomer(customer);
    }
    //read
    public Customer readCustomer(int customerId) throws CustomerException{
        return this.customerAccess.readCustomer(customerId);
    }
    //update
    public void updateCustomer(Customer customer) throws CustomerException{
        this.customerAccess.updateCustomer(customer);
    }
    //delete
    public void deleteCustomer(int customerId) throws CustomerException{
        this.customerAccess.deleteCustomer(customerId);
    }
    //readAll
    public ArrayList<Customer> readAllCustomers() throws CustomerException{
        return this.customerAccess.readAllCustomers();
    }
    //getNextCode
    public int getNextCode() throws NextCodeCustomerException {
        return this.customerAccess.getNextCode();
    }
    //getNumberCustomer
    public int getNumberCustomer() throws NumberCustomerException {
        return this.customerAccess.getNumberCustomer();
    }
    //getLastCustomerId
    public int getLastCustomerId() throws NextCodeCustomerException {
        return this.customerAccess.getLastCustomerId();
    }

}
