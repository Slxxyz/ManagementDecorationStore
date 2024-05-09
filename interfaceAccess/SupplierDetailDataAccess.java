package interfaceAccess;

import exception.SupplierDetailException;
import model.SupplierDetail;

import java.sql.Timestamp;
import java.util.ArrayList;
public interface SupplierDetailDataAccess {
    //create
    public void createSupplierDetail(SupplierDetail supplierDetail) throws SupplierDetailException;
    //read
    public SupplierDetail readSupplierDetail(int supplierDetailId, String supplierName, Timestamp dateAndTimeId) throws SupplierDetailException;
    //readAll
    public ArrayList<SupplierDetail> readAllSupplierDetails() throws SupplierDetailException;

}
