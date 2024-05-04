package model;

import java.time.LocalDate;

public class SaleReport {
    private int code;
    private LocalDate date;

    public SaleReport(int code, LocalDate date) {
        this.code = code;
        this.date = date;
    }
    //////////////////////////////////////////////////////////////////GETTERS
    public int getCode() {
        return code;
    }

    public LocalDate getDate() {
        return date;
    }

    //////////////////////////////////////////////////////////////////SETTERS

    public void setCode(int code) {
        this.code = code;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
