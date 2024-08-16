package model;

import model.SearchOrderLineResults;

import java.util.ArrayList;

public class SearchOrderCustomerResult {

    private int code;
    private String dateAndTime;
    private String methodOfPayment;

    private ArrayList<SearchOrderLineResults> searchOrderLinesResults;

    // Constructeur
    public SearchOrderCustomerResult(int code, String dateAndTime, String methodOfPayment,ArrayList<SearchOrderLineResults> searchOrderLinesResults) {
        this.code = code;
        this.dateAndTime = dateAndTime;
        this.methodOfPayment = methodOfPayment;
        this.searchOrderLinesResults = searchOrderLinesResults;

    }

    // Getters
    public int getCode() {
        return code;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }


    public ArrayList<SearchOrderLineResults> getOrderLines() {
        return searchOrderLinesResults;
    }
}
