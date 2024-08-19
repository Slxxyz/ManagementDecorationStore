package userInterface;

import controller.CustomerController;
import controller.OrderCustomerController;
import controller.OrderLineController;
import controller.ProductController;
import exception.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.awt.event.ActionListener;


public class OrderCustomerPanel extends JPanel implements ActionListener {

    private static final int MIN_QUANTITY_PRODUCT = 1;
    private static final int MAX_QUANTITY_PRODUCT = 15;

    private JLabel methodOfPaymentLabel;
    private JRadioButton isCard;
    private JRadioButton isCash;
    private JRadioButton isCheque;

    private ButtonGroup paymentMethodGroup;
    private SpinnerNumberModel productQuantityModel;
    private JSpinner productQuantityField;
    private DefaultComboBoxModel<Product> productComboBoxModel;
    private JComboBox<Product> productComboBox;
    private JButton addProductButton;
    private DefaultListModel<OrderLine> orderLineModel;
    private JList<OrderLine> orderLineList;
    private JPanel orderPanel;


    private OrderCustomerController orderCustomerController;
    private CustomerController customerController;
    private OrderLineController orderLineController;
    private ProductController productController;

    public OrderCustomerPanel() {
        this.orderPanel = new JPanel();
        this.orderPanel.setLayout(new BorderLayout());

        this.orderCustomerController = new OrderCustomerController();
        this.orderLineController = new OrderLineController();
        this.customerController = new CustomerController();
        this.productController = new ProductController();

        this.methodOfPaymentLabel = new JLabel("Moyen de paiement");
        this.isCash= new JRadioButton("Espèces");
        this.isCard= new JRadioButton("Carte");
        this.isCheque= new JRadioButton("Chèque");

        this.paymentMethodGroup= new ButtonGroup();
        paymentMethodGroup.add(isCash);
        paymentMethodGroup.add(isCard);
        paymentMethodGroup.add(isCheque);
        this.paymentMethodGroup.setSelected(isCash.getModel(), true);


        this.productQuantityModel = new SpinnerNumberModel(MIN_QUANTITY_PRODUCT, MIN_QUANTITY_PRODUCT, MAX_QUANTITY_PRODUCT, 1);
        this.productQuantityField = new JSpinner(productQuantityModel);
        JFormattedTextField textField = ((JSpinner.DefaultEditor) this.productQuantityField.getEditor()).getTextField();
        textField.setEditable(false);

        this.productComboBoxModel = new DefaultComboBoxModel<Product>();
        this.productComboBox = new JComboBox<Product>(productComboBoxModel);

        this.setAllProducts();

        this.addProductButton = new JButton("Ajouter un produit");
        this.addProductButton.addActionListener(this);

        this.orderLineModel = new DefaultListModel<OrderLine>();
        this.orderLineList = new JList<OrderLine>(orderLineModel);
        orderLineList.setVisible(false);

        JPanel orderLineNorthPanel = new JPanel();
        orderLineNorthPanel.setLayout(new GridLayout(1, 2));
        orderLineNorthPanel.add(methodOfPaymentLabel);
        orderLineNorthPanel.add(isCash);
        orderLineNorthPanel.add(isCard);
        orderLineNorthPanel.add(isCheque);


        JPanel orderLineSouthPanel = new JPanel();
        orderLineSouthPanel.setLayout(new GridLayout(1, 2));
        orderLineNorthPanel.add(productComboBox);
        orderLineNorthPanel.add(productQuantityField);
        orderLineNorthPanel.add(addProductButton);

        this.orderPanel.add(orderLineNorthPanel, BorderLayout.NORTH);
        this.orderPanel.add(orderLineSouthPanel, BorderLayout.CENTER);

        this.add(orderPanel, BorderLayout.CENTER);


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
        return orderPanel;
    }

    public String getMethodOfPayment(){
        if(isCard.isSelected()){
            return "Carte";
        }else{
            if(isCash.isSelected()) {
                return "Espèces";
            } else{
                return "Chèque";
            }
        }
    }

    public ArrayList<OrderLine> getOrderLines(){
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
        for(int i=0; i<orderLineModel.getSize(); i++){
            orderLines.add(orderLineModel.getElementAt(i));
        }
        return orderLines;
    }

    public int pointNbCalculator() throws ProductException {
        ArrayList<OrderLine> orderLines = getOrderLines();
        int pointNb = 0;
        for(OrderLine orderLine : orderLines){
            Product product = productController.readProduct(orderLine.getProduct());
            pointNb += product.getLoyaltyPointsValue() * orderLine.getQuantity();
        }
        return pointNb;
    }

    public int identifyLastId() {
        try {
            return orderLineController.getNextCode();
        } catch (NextCodeOrderLineException e) {
            JOptionPane.showMessageDialog(null, "Veuillez réessayer ultérieurement... code:500", "Erreur lors de la creation de la commande", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    protected OrderCustomer getOrderCustomer(int nextCode) {
        String methodOfPayment = this.getMethodOfPayment();
        Timestamp dateAndTime = new Timestamp(System.currentTimeMillis());
        try {
            int currentCustomerCode = customerController.getNextCode();
            return new OrderCustomer(nextCode, dateAndTime, methodOfPayment, currentCustomerCode);
        } catch (NextCodeCustomerException e) {
            JOptionPane.showMessageDialog(null, "Veuillez réessayer ultérieurement... code:500", "Erreur lors de la creation de la commande", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String source = event.getActionCommand();
        String methodOfPayment = this.getMethodOfPayment();
        int idStart = identifyLastId()+orderLineModel.getSize();
        if(source.equals("Ajouter un produit")){
            if(!methodOfPayment.isBlank() && !methodOfPayment.isEmpty()){
                if(productComboBox.getSelectedItem() != null) {
                    Product product = (Product) productComboBox.getSelectedItem();
                    int quantity = (int) productQuantityField.getValue();
                    try {
                        int orderCustomerCode = orderCustomerController.getNextCode();
                        OrderLine orderLine = new OrderLine(idStart,quantity,orderCustomerCode,product.getCode());
                        orderLineModel.addElement(orderLine);
                    } catch ( NextCodeOrderCustomerException exception) {
                        JOptionPane.showMessageDialog(null, "Echec lors du chargement du code de la ligne de commande", "Echec du chargement", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Vous devez saisir un moyen de payement !", "Erreur dans la commande", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //Réinitialiser les champs du formulaire et la liste des produits
    public void resetFields(){
        this.paymentMethodGroup.setSelected(isCash.getModel(), true);
        this.productQuantityField.setValue(MIN_QUANTITY_PRODUCT);
        this.productComboBox.setSelectedIndex(0);
        this.orderLineModel.removeAllElements();
    }

}



