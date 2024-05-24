package interfaceAccess;

import exception.OrderLineException;
import exception.NextCodeOrderLineException;
import model.OrderLine;
import java.util.ArrayList;
public interface OrderLineDataAccess {
    //create
    public void createOrderLine(OrderLine orderLine) throws OrderLineException;
    //readAll
    public ArrayList<OrderLine> readAllOrderLines() throws OrderLineException;
    //readAllFor
    public ArrayList<OrderLine> readAllOrderLinesFor(int orderCode) throws OrderLineException;
    //deleteAllFor
    public void deleteAllOrderLinesFor(int orderCode) throws OrderLineException;
    //getNextCode
    public int getNextCode() throws NextCodeOrderLineException;


}
