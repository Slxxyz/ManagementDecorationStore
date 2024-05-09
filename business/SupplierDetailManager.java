package business;

import dataAccess.SupplierDetailDataBaseAccess;
import interfaceAccess.SupplierDetailDataAccess;
import exception.SupplierDetailException;
import model.SupplierDetail;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SupplierDetailManager {
    private SupplierDetailDataAccess supplierAccess;

    public SupplierDetailManager() {
        setSupplierDetailManager(new SupplierDetailDataBaseAccess());
    }
    public void setSupplierDetailManager(SupplierDetailDataAccess supplierAccess) {
        this.supplierAccess = supplierAccess;
    }
    //create
    public void createSupplierDetail(SupplierDetail supplierDetail) throws SupplierDetailException {
        supplierAccess.createSupplierDetail(supplierDetail);
    }
    //read
    public SupplierDetail readSupplierDetail(int supplierDetailId, String supplierName, Timestamp dateAndTimeId) throws SupplierDetailException {
        return supplierAccess.readSupplierDetail(supplierDetailId, supplierName,dateAndTimeId);
    }
    //readAll
    public ArrayList<SupplierDetail> readAllSupplierDetails() throws SupplierDetailException {
        return supplierAccess.readAllSupplierDetails();
    }
}

