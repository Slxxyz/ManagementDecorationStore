package model;

import java.time.LocalDate;

public class SalesSearchResult {
    private LocalDate date;
    private double salesAmount;

    public SalesSearchResult(LocalDate date, double salesAmount) {
        this.date = date;
        this.salesAmount = salesAmount;
    }

    // GETTERS
    public LocalDate getDate() {
        return date;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    // SETTERS
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }
}
