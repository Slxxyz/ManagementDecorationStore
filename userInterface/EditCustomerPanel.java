package userInterface;

import controller.*;
import exception.*;
import model.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;


public class EditCustomerPanel extends JPanel implements ActionListener{

    private CustomerController customerController;
    private GeneralCustomerPanel generalPanel;

    private DefaultComboBoxModel<Customer> customerSelectionComboBoxModel;
    private JComboBox<Customer> customerSelectionComboBox;
    private JButton customerEditButton;
    private JTabbedPane tabs;



    public EditCustomerPanel(){
        this.setLayout(new BorderLayout());

        this.customerController = new CustomerController();

        this.generalPanel= new GeneralCustomerPanel();


        this.customerSelectionComboBoxModel =new DefaultComboBoxModel<Customer>();
        this.customerSelectionComboBox = new JComboBox<Customer>(customerSelectionComboBoxModel);
        this.customerEditButton = new JButton("Modifier");
        this.customerEditButton.addActionListener(this);


        JPanel customerEditNorthPanel = new JPanel();
        customerEditNorthPanel.setLayout(new FlowLayout());
        customerEditNorthPanel.add(customerSelectionComboBox);
        customerEditNorthPanel.add(customerEditButton);
        this.add(customerEditNorthPanel, BorderLayout.NORTH);

        this.tabs = new JTabbedPane();
        this.tabs.insertTab("Informations générales",null, generalPanel.getPanel(), "Panneau Général",0);

        this.setAllCustomers();

        this.add(this.tabs, BorderLayout.CENTER);
    }

    private void setGeneralCustomer(Customer customer){
        String lastName = customer.getLastName();
        String firstName = customer.getFirstName();
        String gender = customer.getGender();
        int pointNb = customer.getPointNb();
        String telNumber = customer.getTelNumber();
        String mailAddress = customer.getMailAddress();
        LocalDate birthday = customer.getBirthday();
        Boolean isMarried = customer.getIsMarried();
        int cityId = customer.getCityId();

        try{
            this.generalPanel.setLastName(lastName);
            this.generalPanel.setFirstName(firstName);
            this.generalPanel.setGender(gender);
            this.generalPanel.setPointNb(pointNb);
            this.generalPanel.setTelNumber(telNumber);
            this.generalPanel.setMailAddress(mailAddress);
            this.generalPanel.setBirthday(birthday);
            this.generalPanel.setIsMarried(isMarried);
            this.generalPanel.setCity(cityId);

        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la mise à jour des données du client !","Attention !!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setAllCustomers(){
        try {
            ArrayList<Customer> customers = this.customerController.readAllCustomers();
            this.customerSelectionComboBoxModel.addAll(customers);
        }catch (ExceptionType exception){
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCustomer(Customer selectedCustomer){
        try{
            Customer customer = this.generalPanel.getCustomer(selectedCustomer.getNumber());
            if(customer!=null){
                this.customerController.updateCustomer(customer);
            }

        }catch(ExceptionType exception){
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event){
        String source=event.getActionCommand();
        Customer selectedCustomer = (Customer) this.customerSelectionComboBox.getSelectedItem();
        if(source.equals("comboBoxChanged")) {
            if(selectedCustomer!=null){
                this.setGeneralCustomer(selectedCustomer);
                this.generalPanel.setCityForCustomer(selectedCustomer);
            }
        }else{
            if(source.equals("Modifier")){
                if(selectedCustomer!=null) {
                    this.updateCustomer(selectedCustomer);
                    this.customerSelectionComboBoxModel.removeAllElements();
                    this.setAllCustomers();
                }else{
                    JOptionPane.showMessageDialog(null, "Vous devez selectionner un client", "Selectionner un client", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
