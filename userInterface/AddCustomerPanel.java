package userInterface;

import controller.CustomerController;
import controller.OrderCustomerController;
import exception.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerPanel extends JPanel implements ActionListener{
    private GeneralCustomerPanel generalPanel;
    private OrderCustomerPanel orderCustomerPanel;
    private OrderLinePanel orderLinePanel;
    private JButton addCustomerButton;

    private JTabbedPane tabs;

    private CustomerController customerController;
    private OrderCustomerController orderCustomerController;

    public AddCustomerPanel(){
        this.setLayout(new BorderLayout());

        this.customerController = new CustomerController();
        this.orderCustomerController = new OrderCustomerController();

        this.generalPanel = new GeneralCustomerPanel();
        this.orderCustomerPanel = new OrderCustomerPanel();
        this.orderLinePanel = new OrderLinePanel();


        this.addCustomerButton = new JButton("Ajouter un client");
        this.addCustomerButton.addActionListener(this);
        this.add(this.addCustomerButton, BorderLayout.NORTH);

        this.tabs = new JTabbedPane();
        this.tabs.insertTab("Informations générales",null, this.generalPanel.getPanel(), "Panneau général", 0);
        this.tabs.insertTab("Commandes", null, this.orderCustomerPanel.getPanel(), "Panneau des commandes", 1);
        this.tabs.insertTab("Lignes de commande", null, this.orderLinePanel.getPanel(), "Panneau des lignes de commande", 2);
        this.add(this.tabs, BorderLayout.CENTER);
    }

    private int getNextCustomerId(){
        try{
            return customerController.getNextCode();

        }catch(NextCodeCustomerException exception){
            JOptionPane.showMessageDialog(null,"Echec lors du chargement du code du client", "Echec du chargement", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    private int getNextOrderCustomerId(){
        try{
            return orderCustomerController.getNextCode();
        }catch(NextCodeOrderCustomerException exception){
            JOptionPane.showMessageDialog(null,"Echec lors du chargement du code de la commande", "Echec du chargement", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String source = event.getActionCommand();
        int nextCode = this.getNextCustomerId();
        if(source.equals("Ajouter un client")){
            try{
                Customer customer= this.generalPanel.getCustomer(nextCode);
                if(customer!=null){
                    nextCode=this.getNextOrderCustomerId();
                    OrderCustomer orderCustomer = this.orderCustomerPanel.getOrderCustomer(nextCode);
                    if(orderCustomer!=null){
                        this.orderLinePanel.updateOrderLineForOrderCustomer(orderCustomer);
                        this.orderCustomerController.createOrderCustomer(orderCustomer);
                        this.customerController.createCustomer(customer);
                    }else{
                        JOptionPane.showMessageDialog(null, "Lors de la création d'un client, une commande doit obligatoirement lui être associé !", "Erreur dans les informations de la commande", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Veuillez saisir les informations du client", "Erreur dans les informations du client", JOptionPane.ERROR_MESSAGE);
                }
            }catch(ExceptionType exception){
                JOptionPane.showMessageDialog(null,exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
