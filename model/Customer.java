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
}

