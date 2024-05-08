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
    //////////////////////////////////////////////////////////////////GETTERS

    public String getLegalName() {
        return legalName;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getBankingInformation() {
        return bankingInformation;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setBankingInformation(String bankingInformation) {
        this.bankingInformation = bankingInformation;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

}
