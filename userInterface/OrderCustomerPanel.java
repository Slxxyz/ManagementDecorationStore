package userInterface;

import controller.CustomerController;
import controller.OrderCustomerController;
import exception.*;
import model.*;

import javax.swing.*;
import java.sql.Timestamp;



public class OrderCustomerPanel extends JPanel  {

    private OrderCustomerController orderCustomerController;
    private JPanel orderCustomerPanel;
    private JSpinner dateAndTimeField;
    private JLabel methodOfPaymentLabel;
    private JTextField methodOfPaymentField;
    private JLabel dateAndTimeLabel;

    private CustomerController customerController;

    public OrderCustomerPanel() {

        this.orderCustomerController = new OrderCustomerController();
        this.customerController = new CustomerController();

        this.dateAndTimeLabel = new JLabel("Date et heure");
        this.dateAndTimeField = new JSpinner(new SpinnerDateModel());

        this.methodOfPaymentLabel = new JLabel("Moyen de paiement");
        this.methodOfPaymentField = new JTextField();


        this.orderCustomerPanel= new FormBuilder()
                .addLabelAnd(dateAndTimeLabel, dateAndTimeField)
                .addLabelAnd(methodOfPaymentLabel, methodOfPaymentField)
                .build();

    }

    public JPanel getPanel(){
        return orderCustomerPanel;
    }

    protected OrderCustomer getOrderCustomer(int nextCode) {
        Timestamp dateAndTime = new Timestamp(((SpinnerDateModel)dateAndTimeField.getModel()).getDate().getTime());
        String methodOfPayment = methodOfPaymentField.getText();
        if(methodOfPayment!=null){
            try {
                int code = -1;
                code = orderCustomerController.getNextCode();
                int currentCustomerCode = customerController.getNumberCustomer();

                return new OrderCustomer(code, dateAndTime, methodOfPayment, currentCustomerCode);
            } catch (NextCodeOrderCustomerException | NumberCustomerException e) {
                JOptionPane.showMessageDialog(null, "Veuillez réessayer ultérieurement... code:500", "Erreur lors de la creation de la commande", JOptionPane.ERROR_MESSAGE);
            }

        }
        JOptionPane.showMessageDialog(null, "Vous devez saisir un moyen de payement !", "Erreur dans la commande", JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
