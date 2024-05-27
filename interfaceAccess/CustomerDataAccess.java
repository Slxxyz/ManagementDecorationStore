package interfaceAccess;

import exception.CustomerException;
import exception.NextCodeCustomerException;
import exception.NumberCustomerException;
import model.Customer;
import java.util.ArrayList;
public interface CustomerDataAccess {
    //create
    public void createCustomer(Customer customer) throws CustomerException;
    //read
    public Customer readCustomer(int numberCustomer) throws CustomerException;
    //update
    public void updateCustomer(Customer customer) throws CustomerException;
    //delete
    public void deleteCustomer(int numberCustomer) throws CustomerException;
    //readAll
    public ArrayList<Customer> readAllCustomers() throws CustomerException;
    //getNextCode
    public int getNextCode() throws NextCodeCustomerException;
    //getNumberCustomer
    public int getNumberCustomer() throws NumberCustomerException;
    //getLastCustomer

    public int getLastCustomerId() throws NextCodeCustomerException;
}
