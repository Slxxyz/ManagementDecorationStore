package business;

import exception.NextCodeOrderLineException;
import interfaceAccess.OrderLineDataAccess;
import dataAccess.OrderLineDataBaseAccess;
import exception.OrderLineException;
import model.OrderLine;
import java.util.ArrayList;

public class OrderLineManager {
    private OrderLineDataAccess orderLineAccess;
    public OrderLineManager(){
        setOrderLineManager(new OrderLineDataBaseAccess());
    }
    public void setOrderLineManager(OrderLineDataAccess orderLineAccess){
        this.orderLineAccess = orderLineAccess;
    }
    //create
    public void createOrderLine(OrderLine orderLine) throws OrderLineException{
        this.orderLineAccess.createOrderLine(orderLine);
    }
    //readAll
    public ArrayList<OrderLine> readAllOrderLines() throws OrderLineException{
        return this.orderLineAccess.readAllOrderLines();
    }
    //readAllFor
    public ArrayList<OrderLine> readAllOrderLinesFor(int orderLineCode) throws OrderLineException{
        return this.orderLineAccess.readAllOrderLinesFor(orderLineCode);
    }
    //deleteAllFor
    public void deleteAllOrderLinesFor(int orderLineCode) throws OrderLineException{
        this.orderLineAccess.deleteAllOrderLinesFor(orderLineCode);
    }
    //getNextCode
    public int getNextCode() throws NextCodeOrderLineException {
        return this.orderLineAccess.getNextCode();
    }

}
