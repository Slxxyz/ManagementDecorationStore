package model;

import java.time.LocalDate;

public class SalesSearchResult {
    private LocalDate date;
    private int salesAmount;

    public SalesSearchResult(LocalDate date, int salesAmount) {
        this.date = date;
        this.salesAmount = salesAmount;
    }

    // GETTERS
    public LocalDate getDate() {
        return date;
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    // SETTERS
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSalesAmount(int salesAmount) {
        this.salesAmount = salesAmount;
    }
}
