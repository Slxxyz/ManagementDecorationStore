package model;

public class Supplier {
    private String legalName;
    private String streetAndNumber;
    private String location;
    private Integer postalCode;
    private String bankingInformation;
    private String vatNumber;

    public Supplier(String legalName, String streetAndNumber, String location, String bankingInformation) {
        this.legalName = legalName;
        this.streetAndNumber = streetAndNumber;
        this.location = location;
        this.bankingInformation = bankingInformation;
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

    public Integer getPostalCode() {
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

    public void setPostalCode(Integer postalCode) {
        if(postalCode == null || postalCode <= 0){
            this.postalCode = null;
        }else{
            this.postalCode = postalCode;
        }
    }

    public void setBankingInformation(String bankingInformation) {
        this.bankingInformation = bankingInformation;
    }

    public void setVatNumber(String vatNumber) {
        if(vatNumber.isBlank() || vatNumber.isEmpty()){
            this.vatNumber = null;
        }else{
            this.vatNumber = vatNumber;
        }
    }

}
