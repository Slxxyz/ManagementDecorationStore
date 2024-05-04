package model;

import java.time.LocalDateTime;

public class OrderCustomer {
    private int code;
    private LocalDateTime dateAndTime;
    private String methodOfPayement;

    public OrderCustomer(int code, LocalDateTime dateAndTime, String methodOfPayement) {
        this.code = code;
        this.dateAndTime = dateAndTime;
        this.methodOfPayement = methodOfPayement;
    }

    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getMethodOfPayement() {
        return methodOfPayement;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setMethodOfPayement(String methodOfPayement) {
        this.methodOfPayement = methodOfPayement;
    }


}
