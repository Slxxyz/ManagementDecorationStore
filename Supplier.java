package model;

public class Supplier {
    private String legalName;
    private String streetAndNumber;
    private String location;
    private int postalCode;
    private String bankingInformation;
    private String vatNumber;

    public Supplier(String legalName, String streetAndNumber, String location, int postalCode, String bankingInformation, String vatNumber) {
        this.legalName = legalName;
        this.streetAndNumber = streetAndNumber;
        this.location = location;
        this.postalCode = postalCode;
        this.bankingInformation = bankingInformation;
        this.vatNumber = vatNumber;
    }
}
