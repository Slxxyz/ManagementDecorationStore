package controller;

import business.SupplierDetailManager;
import exception.SupplierDetailException;
import model.SupplierDetail;

import java.sql.Timestamp;
import java.util.ArrayList;
public class SupplierDetailController {
    private SupplierDetailManager manager;
    public SupplierDetailController() {
        setManager(new SupplierDetailManager());
    }
    public void setManager(SupplierDetailManager manager) {
        this.manager = manager;
    }
    //create
    public void createSupplierDetail(SupplierDetail supplierDetail)throws SupplierDetailException {
        this.manager.createSupplierDetail(supplierDetail);
    }
    //read
    public SupplierDetail readSupplierDetail(int supplierDetailId, String supplierName, Timestamp dateAndTimeId) throws SupplierDetailException {
        return this.manager.readSupplierDetail(supplierDetailId, supplierName, dateAndTimeId);
    }
    //readAll
    public ArrayList<SupplierDetail> readAllSupplierDetails() throws SupplierDetailException {
        return this.manager.readAllSupplierDetails();
    }
}
