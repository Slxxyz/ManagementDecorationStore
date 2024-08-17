package model;


import controller.CityController;
import exception.CityException;

import java.time.LocalDate;

public class Customer {
    private int numberCustomer;
    private String lastName;
    private String firstName;
    private String gender;
    private int pointNb;
    private String telNumber;
    private String mailAddress;
    private LocalDate birthday;
    private Boolean isMarried;
    private int cityId;


    public Customer(int numberCustomer, String lastName, String firstName, String gender, int pointNb,LocalDate birthday, Boolean isMarried, int cityId) {
        this.numberCustomer = numberCustomer;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.pointNb = pointNb;
        this.birthday = birthday;
        this.isMarried = isMarried;
        this.cityId = cityId;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getNumber() {
        return numberCustomer;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public int getPointNb() {
        return pointNb;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Boolean getIsMarried() {
        return isMarried;
    }

    public int getCityId() {
        return cityId;
    }


    //////////////////////////////////////////////////////////////////SETTERS
    public void setNumber(int numberCustomer) {
        this.numberCustomer = numberCustomer;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPointNb(int pointNb) {
        this.pointNb = pointNb;
    }

    public void setTelNumber(String telNumber) {
        if(telNumber.isBlank() || telNumber.isEmpty()) {
            this.telNumber = null;
        } else {
            this.telNumber = telNumber;
        }
    }

    public void setMailAddress(String mailAddress) {
        if(mailAddress.isBlank() || mailAddress.isEmpty()) {
            this.mailAddress = null;
        } else {
            this.mailAddress = mailAddress;
        }
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setIsMarried(Boolean isMarried) {
        this.isMarried = isMarried;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }



    //////////////////////////////////////////////////////////////////

    // Fonction qui permet de retourner le nom de la ville
    public String getCityName(int cityId) throws CityException {
        CityController cityController = new CityController();
        return cityController.readCity(cityId).getCityName();
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

}

