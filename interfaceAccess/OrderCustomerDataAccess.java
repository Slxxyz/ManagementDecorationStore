package interfaceAccess;

import exception.*;
import model.OrderCustomer;

import java.util.ArrayList;

public class OrderCustomerDataAccess {

    void createOrderCustomer(OrderCustomer orderCustomer) throws OrderCustomerException 
    
    void deleteOrderCustomer(Integer orderCode) throws OrderCustomerException 
   
    void deleteAllOrderCustomer(Integer customerNumber) throws OrderCustomerException 
   
    ArrayList<OrderCustomer> readAllOrderCustomer() throws OrderCustomerException 
   
    ArrayList<OrderCustomer> readAllOrderCustomerFor(Integer customerNumber) throws OrderCustomerException 
       
    OrderCustomer readOrderCustomer(int codeOrderCustomer) throws OrderCustomerException 
   
}
