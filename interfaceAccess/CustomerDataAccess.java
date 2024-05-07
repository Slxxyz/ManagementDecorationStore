package interfaceAccess;

import exception.*;
import model.Customer;

import java.util.ArrayList;

public class CustomerDataAccess {

    void createCustomer(Customer customer) throws CustomerException 
    
    Customer readCustomer() throws CustomerException 

   
    void updateCustomer(Customer customer) throws CustomerException 
      
  
    void deleteCustomer(int numberCustomer) throws CustomerException 

   
    ArrayList<Customer> readAllCustomer() throws CustomerException 
     
    
    int getNextCode() throws NextCodeCustomerException 
   
    
    int getNumberCustomer() throws NumberCustomerException 
     
  
}
