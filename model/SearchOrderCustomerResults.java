package model;

import model.SearchOrderLineResults;

import java.util.ArrayList;

public class SearchOrderCustomerResults {

    private int code;
    private String dateAndTime;
    private String methodOfPayment;

    private ArrayList<SearchOrderLineResult> searchOrderLinesResult;

    // Constructeur
    public SearchOrderCustomerResults(int code, String dateAndTime, String methodOfPayment,ArrayList<SearchOrderLineResult> searchOrderLinesResult) {
        this.code = code;
        this.dateAndTime = dateAndTime;
        this.methodOfPayment = methodOfPayment;
        this.searchOrderLinesResult = searchOrderLinesResult;

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


    public ArrayList<SearchOrderLineResult> getOrderLines() {
        return searchOrderLinesResult;
    }
}