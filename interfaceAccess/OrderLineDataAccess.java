package interfaceAccess;

import exception.OrderLineException;
import model.OrderLine;
import java.util.ArrayList;
public interface OrderLineDataAccess {
    //create
    public void createOrderLine(OrderLine orderLine) throws OrderLineException;
    //readAll
    public ArrayList<OrderLine> readAllOrderLines() throws OrderLineException;
    //readAllFor
    public ArrayList<OrderLine> readAllOrderLinesFor(int orderLineCode) throws OrderLineException;
}
