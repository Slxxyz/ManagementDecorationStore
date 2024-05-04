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

}
