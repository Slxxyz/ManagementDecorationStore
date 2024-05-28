package userInterface;

import controller.OrderCustomerController;
import controller.OrderLineController;

import controller.ProductController;
import exception.*;
import model.OrderCustomer;
import model.Product;
import model.OrderLine;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;


public class OrderLinePanel extends JPanel implements ActionListener{

    private static final int MIN_QUANTITY_PRODUCT = 1;
    private static final int MAX_QUANTITY_PRODUCT = 15;

    private SpinnerNumberModel productQuantityModel;
    private JSpinner productQuantityField;
    private DefaultComboBoxModel<Product> productComboBoxModel;
    private JComboBox<Product> productComboBox;
    private JButton addProductButton;
    private DefaultListModel<OrderLine> orderLineModel;
    private JList<OrderLine> orderLineList;

    private OrderCustomerController orderCustomerController;
    private OrderLineController orderLineController;
    private ProductController productController;

    private JPanel orderLinePanel;

    public OrderLinePanel(){
        this.orderCustomerController = new OrderCustomerController();
        this.orderLineController = new OrderLineController();
        this.productController = new ProductController();

        this.productQuantityModel = new SpinnerNumberModel(MIN_QUANTITY_PRODUCT, MIN_QUANTITY_PRODUCT, MAX_QUANTITY_PRODUCT, 1);
        this.productQuantityField = new JSpinner(productQuantityModel);

        this.productComboBoxModel = new DefaultComboBoxModel<Product>();
        this.productComboBox = new JComboBox<Product>(productComboBoxModel);

        this.setAllProducts();

        this.addProductButton = new JButton("Ajouter un produit");
        this.addProductButton.addActionListener(this);

        this.orderLineModel = new DefaultListModel<OrderLine>();
        this.orderLineList = new JList<OrderLine>(orderLineModel);
        orderLineList.setVisible(false);


        this.orderLinePanel = new JPanel();
        this.orderLinePanel.setLayout(new BorderLayout());

        JPanel orderLineNorthPanel = new JPanel();
        orderLineNorthPanel.setLayout(new GridLayout(1, 2));
        orderLineNorthPanel.add(productComboBox);
        orderLineNorthPanel.add(productQuantityField);

        JPanel orderLineSouthPanel = new JPanel();
        orderLineSouthPanel.setLayout(new GridLayout(1, 1));
        orderLineSouthPanel.add(addProductButton);

        this.orderLinePanel.add(orderLineNorthPanel, BorderLayout.NORTH);
        this.orderLinePanel.add(orderLineSouthPanel, BorderLayout.SOUTH);
    }

    private void setAllProducts(){
        try{
            ArrayList<Product> products = this.productController.readAllProducts();
            this.productComboBoxModel.addAll(products);
            this.productComboBox.setSelectedItem(products.get(0));
        }catch (ProductException exception){
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanel(){
        return orderLinePanel;
    }


    //retourne le model de la liste des lignes de commande
    public void setOrderLinesForOrderCustomer(int orderCustomerCode){
        try{
            ArrayList<OrderLine> orderLines = this.orderLineController.readAllOrderLinesFor(orderCustomerCode);
            this.orderLineModel.clear();
            for(OrderLine orderLine : orderLines){
                this.orderLineModel.addElement(orderLine);
            }
        }catch(OrderLineException exception){
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String source = event.getActionCommand();
        if(source.equals("Ajouter un produit")){
            if(productComboBox.getSelectedItem() != null) {
                Product product = (Product) productComboBox.getSelectedItem();
                int quantity = (int) productQuantityField.getValue();
                try {
                    int orderCustomerCode = orderCustomerController.getNextCode();
                    int orderLineCode = orderLineController.getNextCode();
                    OrderLine orderLine = new OrderLine(orderLineCode,quantity,orderCustomerCode,product.getCode());
                    orderLineModel.addElement(orderLine);
                } catch (NextCodeOrderLineException | NextCodeOrderCustomerException exception) {
                    JOptionPane.showMessageDialog(null, "Echec lors du chargement du code de la ligne de commande", "Echec du chargement", JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateOrderLineForOrderCustomer(OrderCustomer orderCustomer){
        try{
            this.orderLineController.deleteAllOrderLinesFor(orderCustomer.getCode());
            Enumeration<OrderLine> orderLines = orderLineModel.elements();
            while(orderLines.hasMoreElements()){
                OrderLine orderLine = orderLines.nextElement();
                this.orderLineController.createOrderLine(orderLine);
            }
        }catch (ExceptionType exception){
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
