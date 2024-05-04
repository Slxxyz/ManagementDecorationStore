package model;

import java.time.LocalDate;

public class SaleReport {
    private int code;
    private LocalDate date;

    public SaleReport(int code, LocalDate date) {
        this.code = code;
        this.date = date;
    }
}
