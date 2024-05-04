package model;

import java.time.LocalDate;

public class Customer {
    private int number;
    private String lastName;
    private String firstName;
    private Character gender;
    private int nbPoints;
    private String telNumber;
    private String mailAddress;
    private LocalDate birthday;
    private Boolean isMarried;


    public Customer(int number, String lastName, String firstName, Character gender, int nbPoints, String telNumber, String mailAddress, LocalDate birthday, Boolean isMarried) {
        this.number = number;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.nbPoints = nbPoints;
        this.telNumber = telNumber;
        this.mailAddress = mailAddress;
        this.birthday = birthday;
        this.isMarried = isMarried;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getNumber() {
        return number;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Character getGender() {
        return gender;
    }

    public int getNbPoints() {
        return nbPoints;
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
    //////////////////////////////////////////////////////////////////SETTERS
    public void setNumber(int number) {
        this.number = number;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
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

}

