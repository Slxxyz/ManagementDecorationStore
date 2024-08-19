package userInterface;

import controller.CityController;
import exception.CityException;
import model.*;


import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
        //Filtre pour n'autoriser que les lettres et une longueur de 15
        PlainDocument lastNameDoc = (PlainDocument) this.lastNameField.getDocument();
        lastNameDoc.setDocumentFilter(new CompositeFilter(new LetterOnlyFilter(), new LengthFilter(15)));


        this.firstNameLabel = new JLabel("Prénom");
        this.firstNameField = new JTextField();
        //Filtre pour n'autoriser que les lettres et une longueur de 15
        PlainDocument firstNameDoc = (PlainDocument) this.firstNameField.getDocument();
        firstNameDoc.setDocumentFilter(new CompositeFilter(new LetterOnlyFilter(), new LengthFilter(15)));


        this.isMale= new JRadioButton("Homme");
        this.isFemale= new JRadioButton("Femme");
        this.isOther= new JRadioButton("Autre");

        this.genderGroup= new ButtonGroup();
        genderGroup.add(isMale);
        genderGroup.add(isFemale);
        genderGroup.add(isOther);
        this.genderGroup.setSelected(isOther.getModel(), true);

        this.telNumberLabel = new JLabel("Numéro de téléphone");
        this.telNumberField = new JTextField();
        //Filtre pour n'autoriser que les chiffres et une longueur de 10
        PlainDocument telNumberDoc = (PlainDocument) this.telNumberField.getDocument();
        telNumberDoc.setDocumentFilter(new CompositeFilter(new NumberOnlyFilter(), new LengthFilter(10)));

        this.mailAddressLabel = new JLabel("Adresse mail");
        this.mailAddressField = new JTextField();
        //Filtre pour n'autoriser que 50 caractères
        PlainDocument mailAddressDoc = (PlainDocument) this.mailAddressField.getDocument();
        mailAddressDoc.setDocumentFilter(new LengthFilter(50));

        this.birthdayLabel = new JLabel("Date de naissance");

        // Obtention de la date actuelle
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        // Calcul de la date maximale autorisée (18 ans en arrière)
        calendar.add(Calendar.YEAR, -18);
        Date maxDate = calendar.getTime();

        // Création du modèle pour le spinner avec la date maximale
        SpinnerDateModel dateModel = new SpinnerDateModel(maxDate, null, maxDate, Calendar.DAY_OF_MONTH);
        birthdayField = new JSpinner(dateModel);

        // Définition du format de date
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthdayField, "yyyy-MM-dd");
        birthdayField.setEditor(dateEditor);

        // Désactiver l'édition manuelle dans le JSpinner
        JFormattedTextField tf = ((JSpinner.DefaultEditor) birthdayField.getEditor()).getTextField();
        tf.setEditable(false);

        this.isMarried = new JCheckBox("Marié(e)");

        this.cityLabel = new JLabel("Ville");
        this.cityComboBoxModel = new DefaultComboBoxModel<City>();
        this.cityComboBox = new JComboBox<City>(cityComboBoxModel);

        this.generalPanel = new FormBuilder()
                .addLabelAnd(lastNameLabel, lastNameField)
                .addLabelAnd(firstNameLabel, firstNameField)
                .addOnSameLine(new ComponentGroup(GridBagConstraints.WEST, isMale, isFemale, isOther))
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


    //format du numéro de tel
    public String formatTelNumber(String telNumber){
        if(telNumber.startsWith("0")){
            return "+32"+telNumber.substring(1);
        }else{
            if(!telNumber.isBlank()||!telNumber.isEmpty()){
                return "+32"+telNumber;
            }else{
                return "";
            }

        }
    }

    public String getTelNumber() {
        return formatTelNumber(this.telNumberField.getText());
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
                int pointNb = 0;
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

    //Effacer les champs du formulaire
    public void resetFields(){
        this.lastNameField.setText("");
        this.firstNameField.setText("");
        this.genderGroup.setSelected(isOther.getModel(), true);
        this.telNumberField.setText("");
        this.mailAddressField.setText("");
        this.birthdayField.setValue(new Date());
        this.isMarried.setSelected(false);
        this.cityComboBox.setSelectedIndex(0);
    }


    public JPanel getPanel(){
        return this.generalPanel;
    }
}
