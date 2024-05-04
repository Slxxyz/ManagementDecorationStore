package model;

public class CheckOut {
    private int identifier;
    private String status;

    public CheckOut(int identifier, String status) {
        this.identifier = identifier;
        this.status = status;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public int getIdentifier() {
        return identifier;
    }

    public String getStatus() {
        return status;
    }
    //////////////////////////////////////////////////////////////////SETTERS
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
