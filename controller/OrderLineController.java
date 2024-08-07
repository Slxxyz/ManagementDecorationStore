package controller;

import business.OrderLineManager;
import exception.NextCodeOrderLineException;
import exception.OrderLineException;
import model.OrderLine;
import java.util.ArrayList;
public class OrderLineController {
    private OrderLineManager manager;
    public OrderLineController() {
        setManager(new OrderLineManager());
    }
    public void setManager(OrderLineManager manager) {
        this.manager = manager;
    }
    //create
    public void createOrderLine(OrderLine orderLine)throws OrderLineException {
        this.manager.createOrderLine(orderLine);
    }
    //readAll
    public ArrayList<OrderLine> readAllOrderLines() throws OrderLineException {
        return this.manager.readAllOrderLines();
    }
    //readAllOrderLineFor
    public ArrayList<OrderLine> readAllOrderLinesFor(int orderLineCode) throws OrderLineException {
        return this.manager.readAllOrderLinesFor(orderLineCode);
    }
    //deleteAllOrderLineFor
    public void deleteAllOrderLinesFor(int orderLineCode) throws OrderLineException {
        this.manager.deleteAllOrderLinesFor(orderLineCode);
    }
    //getNextCode
    public int getNextCode() throws NextCodeOrderLineException {
        return this.manager.getNextCode();
    }
}
