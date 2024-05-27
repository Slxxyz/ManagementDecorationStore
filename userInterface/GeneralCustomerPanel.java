package userInterface;

import controller.CityController;
import exception.CityException;
import model.*;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class GeneralCustomerPanel extends JPanel{
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JRadioButton isMale;
    private JRadioButton isFemale;
    private JRadioButton isOther;
    private ButtonGroup genderGroup;
    private JLabel pointNbLabel;
    private JSpinner pointNbField;
    private JLabel telNumberLabel;
    private JTextField telNumberField;
    private JLabel mailAddressLabel;
    private JTextField mailAddressField;
    private JLabel birthdayLabel;
    private JSpinner birthdayField;
    private JCheckBox isMarried;
    private JLabel cityLabel;
    private DefaultComboBoxModel<City> cityComboBoxModel;
    private JComboBox<City> cityComboBox;

    private JPanel generalPanel;


    private CityController cityController;



    public GeneralCustomerPanel(){
        this.cityController= new CityController();

        this.lastNameLabel = new JLabel("Nom");
        this.lastNameField = new JTextField();

        this.firstNameLabel = new JLabel("Prénom");
        this.firstNameField = new JTextField();

        this.isMale= new JRadioButton("Homme");
        this.isFemale= new JRadioButton("Femme");
        this.isOther= new JRadioButton("Autre");

        this.genderGroup= new ButtonGroup();
        genderGroup.add(isMale);
        genderGroup.add(isFemale);
        genderGroup.add(isOther);
        this.genderGroup.setSelected(isOther.getModel(), true);


        this.pointNbLabel = new JLabel("Nombre de points");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 1000, 1);
        this.pointNbField = new JSpinner(spinnerModel);

        this.telNumberLabel = new JLabel("Numéro de téléphone");
        this.telNumberField = new JTextField();

        this.mailAddressLabel = new JLabel("Adresse mail");
        this.mailAddressField = new JTextField();

        this.birthdayLabel = new JLabel("Date de naissance");
        birthdayField = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthdayField, "yyyy-MM-dd");
        birthdayField.setEditor(dateEditor);

        this.isMarried = new JCheckBox("Marié(e)");

        this.cityLabel = new JLabel("Ville");
        this.cityComboBoxModel = new DefaultComboBoxModel<City>();
        this.cityComboBox = new JComboBox<City>(cityComboBoxModel);

        this.generalPanel = new FormBuilder()
                .addLabelAnd(lastNameLabel, lastNameField)
                .addLabelAnd(firstNameLabel, firstNameField)
                .addOnSameLine(new ComponentGroup(GridBagConstraints.WEST, isMale, isFemale, isOther))
                .addLabelAnd(pointNbLabel, pointNbField)
                .addLabelAnd(telNumberLabel, telNumberField)
                .addLabelAnd(mailAddressLabel, mailAddressField)
                .addLabelAnd(birthdayLabel, birthdayField)
                .addOnSameLine(new ComponentGroup(GridBagConstraints.WEST, isMarried))
                .addLabelAnd(cityLabel, cityComboBox)
                .build();

        this.setAllCities();
    }

    public String getLastName() {
        return lastNameField.getText();
    }
    public void setLastName (String lastName) {
        lastNameField.setText(lastName);
    }
    public String getFirstName() {
        return firstNameField.getText();
    }
    public void setFirstName (String firstName) {
        firstNameField.setText(firstName);
    }
    public String getGender () {
        if (isMale.isSelected()) {
            return "M";
        } else {
            if (isFemale.isSelected()) {
                return "F";
            } else {
                return "X";
            }
        }
    }
    public void setGender(String gender) {

        String lowercaseGender = gender.toUpperCase();

        char genderLetter = lowercaseGender.charAt(0);
        switch (genderLetter) {
            case 'H':
                this.isMale.setSelected(true);
                break;
            case 'F':
                this.isFemale.setSelected(true);
                break;
            default:
                this.isOther.setSelected(true);
                break;
        }
    }
    public int getPointNb() {
        return (int) this.pointNbField.getValue();
    }
    public void setPointNb(int pointNb) {
        this.pointNbField.setValue(pointNb);
    }
    public String getTelNumber() {
        return this.telNumberField.getText();
    }
    public void setTelNumber(String telNumber) {
        this.telNumberField.setText(telNumber);
    }
    public String getMailAddress() {
        return this.mailAddressField.getText();
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddressField.setText(mailAddress);
    }
    public LocalDate getBirthday() {
        Date date = (Date) this.birthdayField.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public void setBirthday(LocalDate birthday) {
        this.birthdayField.setValue(birthday);
    }
    public Boolean getIsMarried() {
        return this.isMarried.isSelected();
    }
    public void setIsMarried(Boolean isMarried) {
        this.isMarried.setSelected(isMarried);
    }

    public City getCity(){
        return (City) this.cityComboBox.getSelectedItem();
    }
    public void setCity(City city){
        this.cityComboBox.setSelectedItem(city);
    }
    public void setCity(Integer cityId){
        try{
            if(cityId!=null){
                ArrayList<City> cities = this.cityController.readAllCities();
                int iCity = 0;
                while(iCity<cities.size() && cities.get(iCity).getCityId()!=cityId){
                    iCity++;
                }
                City city = cities.get(iCity);
                this.setCity(city);
            }
        }catch (CityException exception){
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setAllCities(){
        try{
            ArrayList<City> cities = this.cityController.readAllCities();
            this.cityComboBoxModel.addAll(cities);
            this.cityComboBox.setSelectedItem(cities.get(0));
        }catch (CityException exception){
            JOptionPane.showMessageDialog(null, exception.getDescription(), exception.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    protected Customer getCustomer(int numberCustomer){
        String lastName= this.getLastName();
        if(!lastName.isBlank() && !lastName.isEmpty()){
            String firstName = this.getFirstName();
            if(!firstName.isBlank() && !firstName.isEmpty()){
                String gender = this.getGender();
                int pointNb = this.getPointNb();
                String telNumber = this.getTelNumber();
                String mailAddress = this.getMailAddress();
                LocalDate birthday = this.getBirthday();
                Boolean isMarried = this.getIsMarried();
                City city = this.getCity();
                Customer customer = new Customer(numberCustomer, lastName, firstName,gender, pointNb, birthday, isMarried, city.getCityId());

                customer.setTelNumber(telNumber);
                customer.setMailAddress(mailAddress);
                return customer;

            }else{
                JOptionPane.showMessageDialog(null, "Le prénom est obligatoire", "Erreur dans le prénom", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Le nom est obligatoire", "Erreur dans le nom", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void setCityForCustomer(Customer customer){
        try{
            int cityId = customer.getCityId();
            int iCity = 0;
            while(iCity<this.cityComboBoxModel.getSize() && this.cityComboBoxModel.getElementAt(iCity).getCityId()!=cityId){
                iCity++;
            }
            if(iCity<this.cityComboBoxModel.getSize()){
                this.cityComboBox.setSelectedIndex(iCity);
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Une erreur s'est produite lors de la lecture des données de la ville.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public JPanel getPanel(){
        return this.generalPanel;
    }
}
